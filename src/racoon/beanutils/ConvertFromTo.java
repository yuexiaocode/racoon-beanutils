package racoon.beanutils;


/**
 * 
 * @author Administrator
 *
 * @param <F>
 * @param <T>
 */
public interface ConvertFromTo<F,T> {
	public T convert(F f);
}
