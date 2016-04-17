package racoon.beanutils.converters;

import org.apache.commons.beanutils.Converter;

public abstract class GenericConverter implements Converter{

	@Override
	public <T> T convert(Class<T> type, Object value) {
		// TODO Auto-generated method stub
		return this.convert(type, null, value);
	}
	
	public abstract <T> T convert(Class<T> type,Class<?> genericType,Object value);
}
