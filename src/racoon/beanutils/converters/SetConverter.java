package racoon.beanutils.converters;

import java.util.Collection;
import java.util.HashSet;

public class SetConverter extends CollectionConverter{

	@Override
	public Collection<Object> newInstance() {
		// TODO Auto-generated method stub
		return new HashSet<Object>();
	}

}
