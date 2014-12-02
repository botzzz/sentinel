/**
 *
 */
package scenario.applications;

import org.apache.log4j.Logger;
import org.junit.Test;

import sentinel.Sentinel;
import xml.IXmlSerializable;

/**
 * @author buissartt
 *
 */
public class ApplicationsTest {

	private static final Logger logger = Logger
			.getLogger(ApplicationsTest.class);

	@Test
	public void testAllApplications() {
		
		ApplicationA appA = new ApplicationA();
		ApplicationB appB = new ApplicationB();
		ApplicationC appC = new ApplicationC();
		ApplicationD appD = new ApplicationD();

		appB.start();
		appC.start();
		appD.start();
		try {
			Thread.sleep(10000);
			appA.start();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
