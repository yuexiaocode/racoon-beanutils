
package racoon.beanutils;

import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

import racoon.beanutils.converters.DateConverter;
import racoon.beanutils.converters.ListConverter;
import racoon.beanutils.converters.SetConverter;
import racoon.beanutils.converters.SqlDateConverter;
import racoon.beanutils.converters.SqlTimeConverter;
import racoon.beanutils.converters.SqlTimestampConverter;


public @SuppressWarnings("rawtypes")
class YXConvertUtilsBean extends ConvertUtilsBean implements Converter{
	
	Map<String,ConvertFromTo> convertFromToes = new HashMap<String,ConvertFromTo>();
	
	public YXConvertUtilsBean() {
		super();
		register(false);
	}
	
	private void register(boolean throwException){
        register(java.util.Date.class, throwException ? new DateConverter(): new DateConverter(null));
        register(java.sql.Date.class, throwException ? new SqlDateConverter() : new SqlDateConverter(null));
        register(java.sql.Time.class, throwException ? new SqlTimeConverter() : new SqlTimeConverter(null));
        register(Timestamp.class, throwException ? new SqlTimestampConverter() : new SqlTimestampConverter(null));
        register(List.class,new ListConverter());
        register(Set.class,new SetConverter());
        register(Map.class,new HashMapConverter());
	
	}
	
	private void register(Class clazz, Converter converter) {
        register(converter, clazz);
    }
	
	static Type[] getActualTypeArguments(Class<? extends ConvertFromTo> clazz){
		
		Type[] types = clazz.getGenericInterfaces();
		for(Type type : types){
			if(type instanceof ParameterizedType){
				ParameterizedType parameterizedType = (ParameterizedType)type;
				Type rawType = parameterizedType.getRawType();
				
				if(rawType==ConvertFromTo.class){
					Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
					
					return actualTypeArguments;
				}
				
		}
		}
		return null;
		
	}
	
	public void addConvertFromTo(ConvertFromTo convertFromTo){
		
		if(convertFromTo!=null){
			Type[] argType = getActualTypeArguments(convertFromTo.getClass());
			convertFromToes.put(argType[0]+"/" + argType[1],convertFromTo);
			this.register(this, (Class)argType[1]);
		}
		
	}
	
	
	

	@SuppressWarnings("unchecked")

	public Object convert(Class clazz, Object value) {
		if(value==null) return null;
		Set<String> keyset = convertFromToes.keySet();
		for(String key : keyset){
			ConvertFromTo convertFromTo = convertFromToes.get(key);
		
			Type[] types = convertFromTo.getClass().getGenericInterfaces();
			for(Type type : types){
				if(type instanceof ParameterizedType){
					ParameterizedType parameterizedType = (ParameterizedType)type;
					Type rawType = parameterizedType.getRawType();
					
					if(rawType==ConvertFromTo.class){
						Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
						
						if(clazz==actualTypeArguments[1] && value.getClass()==actualTypeArguments[0]){
							return convertFromTo.convert(value);
						}
					}
					
				}
			}
		}
		return null;
	}
	
}