package racoon.beanutils.converters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("rawtypes") 
public abstract class DateTimeConverter extends org.apache.commons.beanutils.converters.DateTimeConverter{
	private static Log logger = LogFactory.getLog(DateTimeConverter.class);
	public DateTimeConverter() {
		super();
	}

	public DateTimeConverter(Object defaultValue) {
		super(defaultValue);
	}



	@SuppressWarnings("unchecked")
	@Override
	protected Object convertToType(Class targetType, Object value) throws Exception {
		if(value instanceof String){
			try {
				return convertFromString(getDefaultType(),(String)value);
			} catch (Exception e) {
				
				logger.warn("无法将字符串'"+value+"转换为日期' for "+e.getMessage());
				throw e;
			}
		}
		else{
			return super.convertToType(targetType, value);
		}
	}
	

	protected abstract Object convertFromString(Class targetType, String value)  throws Exception;
}
