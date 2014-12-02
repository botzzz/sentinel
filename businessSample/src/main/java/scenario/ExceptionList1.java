package scenario;

import java.util.IllegalFormatException;

import org.junit.internal.AssumptionViolatedException;

public class ExceptionList1 extends AbstractExceptionList {

	@Override
	public void init() {
		this.addException(NullPointerException.class, 2);
		this.addException(null, 3);
	}

}
