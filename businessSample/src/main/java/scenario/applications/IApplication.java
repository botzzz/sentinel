/**
 *
 */
package scenario.applications;

import xml.IXmlSerializable;

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
	 *
	 * @param e
	 */
	void onError(IXmlSerializable messageOriginal, Exception e);

}
