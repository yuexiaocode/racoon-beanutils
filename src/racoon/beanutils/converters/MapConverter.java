package racoon.beanutils.converters;

import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.Converter;

import racoon.beanutils.YXBeanUtils;
import racoon.beanutils.YXConvertUtilsBean;

public abstract class MapConverter extends GenericConverter{
	private YXConvertUtilsBean yxConvertUtilsBean;

	public MapConverter(YXConvertUtilsBean yxConvertUtilsBean) {
		super();
		this.yxConvertUtilsBean = yxConvertUtilsBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Class<?>[] genericType, Object value) {
		if(value==null) return null;
		try {
			Map<Object,Object> map = newInstance();
			
			if(value instanceof Map){
				Map<?,?> originColls = (Map<?,?>)value;
				Set<?> keyset = originColls.keySet();
				for(Object key : keyset){
					Class<?> keyClass = genericType[0];
					Converter convert = yxConvertUtilsBean.lookup(keyClass);
					
					if(convert!=null){
						key = convert.convert(keyClass, key);
					}
					
					Object destValue = genericType[1].newInstance();
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
