package racoon.beanutils.converters;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("rawtypes") 
public class SqlTimeConverter extends DateTimeConverter{
	 /**
     * Construct a <b>java.sql.Time</b> <i>Converter</i> that throws
     * a <code>ConversionException</code> if an error occurs.
     */
    public SqlTimeConverter() {
        super();
    }

    /**
     * Construct a <b>java.sql.Time</b> <i>Converter</i> that returns
     * a default value if an error occurs.
     *
     * @param defaultValue The default value to be returned
     * if the value to be converted is missing or an error
     * occurs converting the value.
     */
    public SqlTimeConverter(Object defaultValue) {
        super(defaultValue);
    }

    /**
     * Return the default type this <code>Converter</code> handles.
     *
     * @return The default type this <code>Converter</code> handles.
     * @since 1.8.0
     */
    @SuppressWarnings("unchecked")
	protected Class getDefaultType() {
        return Time.class;
    }

    /**
     * Return a <code>DateFormat<code> for the Locale.
     * @param locale TODO
     * @param timeZone TODO
     *
     * @return The DateFormat.
     * @since 1.8.0
     */
    protected DateFormat getFormat(Locale locale, TimeZone timeZone) {
        DateFormat format = null;
        if (locale == null) {
            format = DateFormat.getTimeInstance(DateFormat.SHORT);
        } else {
            format = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
        }
        if (timeZone != null) {
            format.setTimeZone(timeZone);
        }
        return format;
    }

	@Override
	protected Object convertFromString(Class targetType, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
