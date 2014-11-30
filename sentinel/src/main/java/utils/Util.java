package utils;

/**
 *
 */
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Utility class.
 *
 * @author buissartt
 *
 */
public class Util {

	/**
	 * Generate a universally unique identifiers.
	 *
	 * @return a universally unique identifiers
	 */
	public static final String generateUUID() {
		return String.valueOf(UUID.randomUUID());
	}

	/**
	 * Get the current time Timestamp
	 *
	 * @return current timestamp to the split millisecond
	 */
	public final static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Get the current Timestamp in Long
	 *
	 * @return the current time in Long
	 */
	public final static Long getCurrentTimeInLong() {
		return getCurrentTimestamp().getTime();
	}
}