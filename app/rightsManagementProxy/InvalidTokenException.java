package rightsManagementProxy;

/**
 * Exception that is thrown when an invalid token is used
 * @author Marvin
 */
public class InvalidTokenException extends Exception {

  /**
   * Creates a new instance of <code>InvalidTokenException</code> without detail message.
   */
  public InvalidTokenException() {
  }

  /**
   * Constructs an instance of <code>InvalidTokenException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public InvalidTokenException(String msg) {
    super(msg);
  }
}
