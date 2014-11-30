/**
 *
 */
package sentinel;

/**
 * @author buissartt
 *
 */
public interface ISentinel {

	public void init(String xmlBusinessContent);

	public void error(String errorMessage);

	public void logContext();

}
