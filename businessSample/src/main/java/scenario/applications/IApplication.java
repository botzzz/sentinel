/**
 *
 */
package scenario.applications;

/**
 * @author buissartt
 *
 */
public interface IApplication {

	/**
	 * execute l'application
	 */
	public void run();

	/**
	 * Things to do on error.
	 * @param e
	 */
	void onError(Exception e);

}
