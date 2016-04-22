package racoon.beanutils.converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

import racoon.beanutils.YXBeanUtils;

public final class MethodsUtils {
	public static Object convert(ConvertUtilsBean convertUtilsBean, Class<?> clazz, Object value) throws IllegalAccessException, InvocationTargetException{
		Converter convert = convertUtilsBean.lookup(clazz);
	
		if(convert==null){
			if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())){
				throw new IllegalArgumentException("can't newInstance class "+clazz +",it is an interface or abstract class.");
			}
			
			try {
				Object destValue = clazz.newInstance();
				YXBeanUtils.copyProperties(destValue, value);
				return destValue;
			} catch (InstantiationException e) {
				throw new IllegalArgumentException(e);
			}
		}
		else{
			return convert.convert(clazz, value);
		}
	}
}
