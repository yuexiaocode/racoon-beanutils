package racoon.beanutils.test.beans;

public class G<T> {
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {

		
		this.t = t;
	}

	public G(T t) {
		super();
		this.t = t;
	}

	public G() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
