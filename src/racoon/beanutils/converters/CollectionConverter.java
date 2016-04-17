package racoon.beanutils.converters;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Collection;

import racoon.beanutils.YXBeanUtils;

public abstract class CollectionConverter extends GenericConverter{
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Class<?> genericType, Object value) {
		if(value==null) return null;
		try {
			Collection<Object> colls = newInstance();
			
			if(value instanceof Iterable){
				Iterable<?> originColls = (Iterable<?>)value;
				for(Object item : originColls){
					Object destItem = genericType.newInstance();
					YXBeanUtils.copyProperties(destItem, item);
					colls.add(destItem);
				}
				return (T) colls;
			}
			else if(value.getClass().isArray()){
				int length = Array.getLength(value);
				for(int i = 0 ; i < length; i ++){
					Object destItem = genericType.newInstance();
					YXBeanUtils.copyProperties(destItem, Array.get(value, i));
					colls.add(destItem);
				}
				return (T) colls;
			}
			else return null;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public abstract Collection<Object> newInstance();
}
