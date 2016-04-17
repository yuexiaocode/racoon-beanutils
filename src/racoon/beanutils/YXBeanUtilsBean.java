package racoon.beanutils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

import racoon.beanutils.converters.GenericConverter;

public class YXBeanUtilsBean extends BeanUtilsBean{

	public YXBeanUtilsBean() {
		super();
	}

	public YXBeanUtilsBean(ConvertUtilsBean convertUtilsBean,
			RacoonPropertyUtilsBean propertyUtilsBean) {
		super(convertUtilsBean, propertyUtilsBean);
		
	}

	public YXBeanUtilsBean(ConvertUtilsBean convertUtilsBean) {
		this(convertUtilsBean,new RacoonPropertyUtilsBean());
	}

	@Override
	public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		try {
			PropertyDescriptor descriptor = getPropertyUtils().
			        getPropertyDescriptor(bean, name);
			
			Field field = bean.getClass().getDeclaredField(name);
			Class<?> type= field.getType();
			
			Type genericType = field.getGenericType();
			
			if(genericType instanceof ParameterizedType){
				ParameterizedType parameterizedType = (ParameterizedType)genericType;
				Type genericArgType = parameterizedType.getActualTypeArguments()[0];
				descriptor.getWriteMethod().invoke(bean, convertGenericForCopy(value, type,(Class<?>)genericArgType));
			}
			else if(genericType instanceof TypeVariable){
				
				descriptor.getWriteMethod().invoke(bean, convertTypeVariableForCopy(value, type,name));
			}
			else{
				super.copyProperty(bean, name, value);
			}
		} catch (Exception e) {
			return;
		}
	}
	
	private static final ThreadLocal<Map<String, Class<?>>> convertMapTL = new ThreadLocal<Map<String,Class<?>>>();		
	
	public void copyProperties(Object dest, Object origin,
			Map<String, Class<?>> convertMap) throws IllegalAccessException, InvocationTargetException {
		convertMapTL.set(convertMap);
		copyProperties(dest, origin);
	}
	public Object convertGenericForCopy(Object value,Class<?> type,Class<?> genericType){
		Converter converter = getConvertUtils().lookup(type);
        if (converter != null && converter instanceof GenericConverter) {
        	return ((GenericConverter)converter).convert(type, genericType, value);
        } else {
            return null;
        }
	}
	
	public Object convertTypeVariableForCopy(Object value,Class<?> type,String name){
		Converter converter = getConvertUtils().lookup(convertMapTL.get().get(name));
        if (converter != null) {
        	if(converter instanceof GenericConverter){
        		return ((GenericConverter)converter).convert(type, type, value);
        	}
        	else return converter.convert(type, value);
        } else {
            return null;
        }
	}
	
	
}
