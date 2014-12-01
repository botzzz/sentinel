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
public class ApplicationBTest {
	private static final Logger logger = Logger
			.getLogger(ApplicationATest.class);

	@Test
	public void runTest() {
		ApplicationB appB = new ApplicationB() {

			public void onError(IXmlSerializable messageOriginal, Exception e) {
				logger.error(e);
			}

		};
		appB.run();
	}

}
