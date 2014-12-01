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
		
		ApplicationA appA = new ApplicationA() {
			public void onError(IXmlSerializable messageOriginal, Exception e) {
				Sentinel sentinel = new Sentinel();

				// sentinel.init(Util.generateUUID(),
				// messageOriginal.convertToString(), "Application A",
				// null, FlowType.PRODUCED);
				sentinel.error("error during Application A", e);
			}
		};
		ApplicationB appB = new ApplicationB() {

			public void onError(IXmlSerializable messageOriginal, Exception e) {
				// TODO Auto-generated method stub

			}

		};
		ApplicationC appC = new ApplicationC() {

			public void onError(IXmlSerializable messageOriginal, Exception e) {
				// TODO Auto-generated method stub

			}

		};

		ApplicationD appD = new ApplicationD() {

			public void onError(IXmlSerializable messageOriginal, Exception e) {
				// TODO Auto-generated method stub

			}

		};

		// appA.run();
		appB.start();
		appC.start();
		appD.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		appA.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
