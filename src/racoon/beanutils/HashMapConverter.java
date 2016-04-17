package racoon.beanutils;

import java.util.HashMap;
import java.util.Map;

import racoon.beanutils.converters.MapConverter;

public class HashMapConverter extends MapConverter {

	@Override
	public Map<Object, Object> newInstance() {
		// TODO Auto-generated method stub
		return new HashMap<Object, Object>();
	}

}
