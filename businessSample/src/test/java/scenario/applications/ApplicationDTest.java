/**
 *
 */
package scenario.applications;

import org.junit.Test;

import xml.IXmlSerializable;

/**
 * @author buissartt
 *
 */
public class ApplicationDTest {
	@Test
	public void runTest() {
		ApplicationD appD = new ApplicationD() {

			public void onError(IXmlSerializable messageOriginal, Exception e) {
			}

		};
		appD.run();
	}
}
