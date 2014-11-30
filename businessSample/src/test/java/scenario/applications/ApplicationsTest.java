/**
 *
 */
package scenario.applications;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author buissartt
 *
 */
public class ApplicationsTest {

	private static final Logger logger = Logger
			.getLogger(ApplicationsTest.class);

	@Test
	public void testAllApplications() {
		logger.debug("JPDIPOZJDPZO");
		ApplicationA appA = new ApplicationA() {
			public void onError(Exception e) {
				logger.error("an error occurred classname" + e.getStackTrace());
				logger.error("an error occurred", e);
			}
		};
		ApplicationB appB = new ApplicationB() {
			public void onError(Exception e) {
				logger.error("an error occurred classname" + e.getStackTrace());
				logger.error("an error occurred", e);
			}

		};
		ApplicationC appC = new ApplicationC() {
			public void onError(Exception e) {
				logger.error("an error occurred classname" + e.getStackTrace());
				logger.error("an error occurred", e);
			}
		};
		;
		ApplicationD appD = new ApplicationD() {
			public void onError(Exception e) {
				logger.error("an error occurred classname" + e.getStackTrace());
				logger.error("an error occurred", e);
			}
		};
		;

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
