package racoon.beanutils.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("rawtypes")
public final class DateConverter extends DateTimeConverter {
	
	private static Log logger = LogFactory.getLog(DateTimeConverter.class);
	
	private static final List<SimpleDateFormat> StringToDatePatterns = new ArrayList<SimpleDateFormat>();
	
	static{
		StringToDatePatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		StringToDatePatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		StringToDatePatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
		StringToDatePatterns.add(new SimpleDateFormat("yyyy-MM-dd HH"));
		StringToDatePatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
		StringToDatePatterns.add(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"));
		StringToDatePatterns.add(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"));    
		StringToDatePatterns.add(new SimpleDateFormat("MM/dd/yyyy HH:mm"));       
		StringToDatePatterns.add(new SimpleDateFormat("MM/dd/yyyy HH"));          
		StringToDatePatterns.add(new SimpleDateFormat("MM/dd/yyyy"));
	}
    /**
     * Construct a <b>java.util.Date</b> <i>Converter</i> that throws
     * a <code>ConversionException</code> if an error occurs.
     */
    public DateConverter() {
        super();
    }

    /**
     * Construct a <b>java.util.Date</b> <i>Converter</i> that returns
     * a default value if an error occurs.
     *
     * @param defaultValue The default value to be returned
     * if the value to be converted is missing or an error
     * occurs converting the value.
     */
    public DateConverter(Object defaultValue) {
        super(defaultValue);
    }

    /**
     * Return the default type this <code>Converter</code> handles.
     *
     * @return The default type this <code>Converter</code> handles.
     */
    
	@SuppressWarnings("unchecked")
	protected Class getDefaultType() {
        return Date.class;
    }

	@Override
	protected Object convertFromString(Class targetType, String value) throws Exception {
		ParseException ex = null;
		for(SimpleDateFormat sdf : StringToDatePatterns){
			try {
				return sdf.parse(value.toString());
			} catch (ParseException e) {
				ex =e;
				System.out.println("try parsing ["+value+"] to date for excetion:"+e.getMessage() );
				logger.debug("Try parsing ["+value+"] to date for excetion:"+e.getMessage());
			}
		}
		throw ex==null?new IllegalArgumentException(value +" can't be converted to " + targetType):ex;
	}

}
