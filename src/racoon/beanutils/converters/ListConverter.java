package racoon.beanutils.converters;

import java.util.ArrayList;
import java.util.Collection;

public class ListConverter extends CollectionConverter{

	@Override
	public Collection<Object> newInstance() {
		return new ArrayList<Object>();
	}
	
}
