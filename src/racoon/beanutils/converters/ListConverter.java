package racoon.beanutils.converters;

import java.util.ArrayList;
import java.util.Collection;

import racoon.beanutils.YXConvertUtilsBean;

public class ListConverter extends CollectionConverter{

	public ListConverter(YXConvertUtilsBean yxConvertUtilsBean) {
		super(yxConvertUtilsBean);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Object> newInstance() {
		return new ArrayList<Object>();
	}
	
}
