package racoon.beanutils.converters;

import java.util.Collection;
import java.util.HashSet;

import racoon.beanutils.YXConvertUtilsBean;

public class SetConverter extends CollectionConverter{

	public SetConverter(YXConvertUtilsBean yxConvertUtilsBean) {
		super(yxConvertUtilsBean);
	}

	@Override
	public Collection<Object> newInstance() {
		return new HashSet<Object>();
	}

}
