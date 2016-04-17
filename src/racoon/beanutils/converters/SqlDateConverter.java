package racoon.beanutils.converters;

import java.sql.Date;

@SuppressWarnings("rawtypes") 
public class SqlDateConverter extends DateTimeConverter{
	/**
     * Construct a <b>java.sql.Date</b> <i>Converter</i> that throws
     * a <code>ConversionException</code> if an error occurs.
     */
    public SqlDateConverter() {
        super();
    }

    /**
     * Construct a <b>java.sql.Date</b> <i>Converter</i> that returns
     * a default value if an error occurs.
     *
     * @param defaultValue The default value to be returned
     * if the value to be converted is missing or an error
     * occurs converting the value.
     */
    public SqlDateConverter(Object defaultValue) {
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
        return Date.class;
    }

	@Override
	protected Object convertFromString(Class targetType, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
