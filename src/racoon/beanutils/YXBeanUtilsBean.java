package racoon.beanutils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

import racoon.beanutils.converters.GenericConverter;
import racoon.beanutils.converters.MethodsUtils;

public class YXBeanUtilsBean extends BeanUtilsBean{
	
	private static class OrginDestPair{
		private Object origin;
		private Object dest;
		public OrginDestPair(Object origin, Object dest) {
			super();
			this.origin = origin;
			this.dest = dest;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((origin == null) ? 0 : origin.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrginDestPair other = (OrginDestPair) obj;
			if (origin == null) {
				if (other.origin != null)
					return false;
			} else if (!origin.equals(other.origin))
				return false;
			return true;
		}
		
		
	}
	
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
	
	/**
	 * 为了解决环路引用出现的栈溢出异常
	 */
	ThreadLocal<Stack<OrginDestPair>> refChain = new ThreadLocal<Stack<OrginDestPair>>();
	
	@Override
	public void copyProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {

		Stack<OrginDestPair> stack = refChain.get();
		if(stack==null){
			refChain.set(stack=new Stack<OrginDestPair>());
		}
		stack.push(new OrginDestPair(orig,dest));
		super.copyProperties(dest, orig);
		stack.pop();
	}

	@Override
	public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		
		try {
			PropertyDescriptor descriptor = getPropertyUtils().
			        getPropertyDescriptor(bean, name);
			Stack<OrginDestPair> stack = refChain.get();
			OrginDestPair pair = new OrginDestPair(value, null);
			
			int lastIndex = stack.lastIndexOf(pair);
			
			Field field = bean.getClass().getDeclaredField(name);
			Class<?> type= field.getType();
			Type genericType = field.getGenericType();
			boolean isGeneric = genericType instanceof ParameterizedType || genericType instanceof TypeVariable; 
			
			if(isGeneric){
				Object destValue = null;
				if(genericType instanceof ParameterizedType){
					ParameterizedType parameterizedType = (ParameterizedType)genericType;
					Type[] genericArgType = parameterizedType.getActualTypeArguments();
					Class<?>[] classes = new Class<?>[genericArgType.length];
					for(int i = 0; i < genericArgType.length; i ++){
						classes[i] = (Class<?>) genericArgType[i];
					}
					destValue = convertGenericForCopy(value, type,classes);
					
				}
				else if(genericType instanceof TypeVariable){
					destValue =  convertTypeVariableForCopy(value, type,name);
				}
				pair.dest = destValue;
				stack.push(pair);
				descriptor.getWriteMethod().invoke(bean, destValue);
				stack.pop();
			}
			else if(lastIndex>=0 && descriptor.getPropertyType().isInstance(stack.get(lastIndex).dest)) {
				descriptor.getWriteMethod().invoke(bean, stack.get(lastIndex).dest);
			}
			else if(!type.isInstance(value) && value!=null){
				descriptor.getWriteMethod().invoke(bean, MethodsUtils.convert(this.getConvertUtils(), type, value));
			}
			else{
				super.copyProperty(bean, name, value);
				
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	private static final ThreadLocal<Map<String, Class<?>>> convertMapTL = new ThreadLocal<Map<String,Class<?>>>();		
	
	public void copyProperties(Object dest, Object origin,
			Map<String, Class<?>> convertMap) throws IllegalAccessException, InvocationTargetException {
		convertMapTL.set(convertMap);
		copyProperties(dest, origin);
	}
	public Object convertGenericForCopy(Object value,Class<?> type,Class<?>[] genericType){
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
        		return ((GenericConverter)converter).convert(type, new Class<?>[]{type}, value);
        	}
        	else return converter.convert(type, value);
        } else {
            return null;
        }
	}
	
	
}
