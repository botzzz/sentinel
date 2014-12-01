package scenario;

import java.util.IllegalFormatException;

import org.junit.internal.AssumptionViolatedException;

public class ExceptionList1 extends AbstractExceptionList {

	@Override
	public void init() {
		this.addException(NullPointerException.class, 2);
		this.addException(null, 3);
		this.addException(IllegalFormatException.class, 500);
		this.addException(ArrayIndexOutOfBoundsException.class, 500);
		this.addException(AssumptionViolatedException.class, 500);
	}

}
