package racoon.beanutils.converters;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Collection;

import org.apache.commons.beanutils.Converter;

import racoon.beanutils.YXBeanUtils;
import racoon.beanutils.YXConvertUtilsBean;

public abstract class CollectionConverter extends GenericConverter{
	private YXConvertUtilsBean yxConvertUtilsBean;
	
	public CollectionConverter(YXConvertUtilsBean yxConvertUtilsBean) {
		this.yxConvertUtilsBean = yxConvertUtilsBean;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Class<?> genericType[], Object value) {
		if(value==null) return null;
		try {
			Collection<Object> colls = newInstance();
			Class<?> genericType0 = genericType[0];
			if(value instanceof Iterable){
				Iterable<?> originColls = (Iterable<?>)value;
				for(Object item : originColls){
					
					Class<?> itemClass = item.getClass();
					Object destItem =  null;
					if(genericType0.isAssignableFrom(itemClass)){
						destItem = item;
					}
					else{
						destItem = MethodsUtils.convert(yxConvertUtilsBean, genericType0, item);
						/*Converter convert = yxConvertUtilsBean.lookup(genericType0);
						if(convert==null){
							if(genericType0.isInterface() || Modifier.isAbstract(genericType0.getModifiers())){
								throw new IllegalArgumentException("can't newInstance class "+genericType0 +",it is an interface or abstract class.");
							}
							destItem = genericType0.newInstance();
							YXBeanUtils.copyProperties(destItem, item);
						}
						else{
							
							destItem = convert.convert(genericType0, item);
						}*/
					}
					
					
					colls.add(destItem);
				}
				return (T) colls;
			}
			else if(value.getClass().isArray()){
				int length = Array.getLength(value);
				for(int i = 0 ; i < length; i ++){
					Object destItem = genericType0.newInstance();
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
