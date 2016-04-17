package racoon.beanutils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;


public class YXConverterUtils extends ConvertUtils{
	private static YXConvertUtilsBean yxConverterUtilsBean;
	

	protected static YXConvertUtilsBean getInstance(){
		init();
		return yxConverterUtilsBean;
		
	}
	
	public static void register(Converter converter, Class<?> clazz) {
		init();
		yxConverterUtilsBean.register(converter, clazz);
    }
	public static void addConvertFromTo(@SuppressWarnings("rawtypes") ConvertFromTo convertFromTo){
		init();
		yxConverterUtilsBean.addConvertFromTo(convertFromTo);
		
	}
	
	public static void init(){
		if(yxConverterUtilsBean==null){
			yxConverterUtilsBean = new YXConvertUtilsBean();
		}
	}

}
	
	
