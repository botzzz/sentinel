/**
 *
 */
package scenario.applications;

import org.apache.log4j.Logger;
import org.junit.Test;

import xml.IXmlSerializable;

/**
 * @author buissartt
 *
 */
public class ApplicationCTest {

	@Test
	public void runTest() {
		ApplicationC appC = new ApplicationC() {

			public void onError(IXmlSerializable messageOriginal, Exception e) {
			}

		};
		appC.run();
	}

}
