package scenario;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExceptionList {

	/**
	 * Exception list
	 */
	private List<Exception> scenarioList;

	/**
	 * Current index of the list.
	 */
	private int currentIndex;

	/**
	 * Constructor.
	 */
	public AbstractExceptionList() {
		this.scenarioList = new ArrayList<Exception>();
		this.currentIndex = 0;
		this.init();
	}

	/**
	 * Throw the next exception of the list.
	 */
	public void next() throws Exception {
		Exception e = scenarioList.get((currentIndex++ % (scenarioList.size() - 1)));
		if(e != null){
			throw e;
		}
	}

	/**
	 * Add the exception to the list.
	 *
	 * @param exception
	 *            the exception to add.
	 * @param n
	 *            the number of exception to add.
	 */
	public void addException(Class<? extends Exception> exception, int n) {
		try {
			for (int i = 0; i < n; i++) {
				Constructor<?> ctor = exception.getConstructor();
				Object object = ctor.newInstance();
				this.scenarioList.add((Exception) object);
			}
		} catch (Exception e) {
			// NOTHING TO DO
		}
	}

	/**
	 * Initialize the exception generator.
	 */
	public abstract void init();

}
