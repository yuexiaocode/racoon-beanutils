package racoon.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class YXBeanUtils extends BeanUtils{
	
	private static YXBeanUtilsBean yxBeanUtilsBean;
	
	public static void copyProperties(Object dest,Object origin) throws IllegalAccessException, InvocationTargetException{
		init();
		yxBeanUtilsBean.copyProperties(dest, origin);
	}
	public static void copyProperties(Object dest,Object origin,Map<String,Class<?>> convertMap) throws IllegalAccessException, InvocationTargetException{
		init();
		yxBeanUtilsBean.copyProperties(dest, origin,convertMap);
	}
	
	private static void init(){
		if(yxBeanUtilsBean==null){
			 yxBeanUtilsBean = new YXBeanUtilsBean(YXConverterUtils.getInstance(),new RacoonPropertyUtilsBean());
		}
	}
	
	
}


/*class MyDateConveter implements Converter {
	



	@SuppressWarnings({"unchecked", "rawtypes" })
	@Override
	public Object convert(Class type, Object value) {

		Class<Date> clazz = java.util.Date.class;
		if(!clazz.isAssignableFrom(type)){
			throw new IllegalArgumentException("this converter only accepts type (extends) java.util.Date");
		}
		
		Date date = null;
		
		if(value==null) return null;
		
		if(value instanceof String){
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse((String)value);
			} catch (ParseException e) {
				throw new IllegalArgumentException("the value can't be parse to Date");
			}
		}
		else if(value instanceof Long || value instanceof Integer){
			date = new Date((Long)value);
		}
		else if(value instanceof Calendar){
			date = ((Calendar)value).getTime();
		}
		if(type==java.util.Date.class) return date;
		if(type==java.sql.Date.class) return new java.sql.Date(date.getTime());
		if(type==java.sql.Timestamp.class) return new java.sql.Timestamp(date.getTime());
		Constructor<?>[] constructors = type.getConstructors();
		
	
		Object param = null;
		Constructor theConstructor = null;
		for(Constructor constructor : constructors){
			theConstructor = constructor;
			constructor.setAccessible(true);

			Class<?>[] types= constructor.getParameterTypes();
			if(null!=types && types.length==1){
				Class<?> type0 = types[0];
				type0.getTypeParameters();
				if(type0==java.util.Date.class){
					param = date;
					break;
				}
				else if(type0==java.lang.Long.class || type0==Long.TYPE){
					param = date.getTime();
					break;
				}
				else if(type0==value.getClass()){
					param = value;
					break;
				}
			}
			
		}
		if(theConstructor!=null && param!=null){
			theConstructor.setAccessible(true);
			try {
				return theConstructor.newInstance(param);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		return null;
		
	}
}
class DateToLongConveter implements Converter {
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		
		
		if(type!=Long.class || type==Long.class){
			throw new IllegalArgumentException("this converter only accepts java.lang.Long");
		}
		if(value instanceof Date){
			return ((Date)value).getTime();
		}
		else if(value instanceof Number){
			((Number)value).longValue();
		}
		else{
			String text = value.toString();
			Integer.valueOf(text);
		}
		return null;
	}
}*/
