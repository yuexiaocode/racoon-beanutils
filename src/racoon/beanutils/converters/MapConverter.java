package racoon.beanutils.converters;

import java.util.Map;
import java.util.Set;

import racoon.beanutils.YXBeanUtils;

public abstract class MapConverter extends GenericConverter{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Class<?> genericType, Object value) {
		if(value==null) return null;
		try {
			Map<Object,Object> map = newInstance();
			
			if(value instanceof Map){
				Map<?,?> originColls = (Map<?,?>)value;
				Set<?> keyset = originColls.keySet();
				for(Object key : keyset){
					Object destValue = genericType.newInstance();
					YXBeanUtils.copyProperties(destValue, originColls.get(key));
					map.put(key,destValue);
				}
				return (T) map;
			}
			
			else return null;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public abstract Map<Object,Object> newInstance();
}
