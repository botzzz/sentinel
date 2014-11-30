package scenario;

public class ExceptionListManager {

	public volatile static AbstractExceptionList LIST = new ExceptionList1();

	private ExceptionListManager(){
	}
}
