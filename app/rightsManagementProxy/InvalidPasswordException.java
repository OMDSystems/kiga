package rightsManagementProxy;

/**
 * Exception that is thrown when an invalid password is used
 * @author Marvin
 */
public class InvalidPasswordException extends Exception {

  /**
   * Creates a new instance of <code>InvalidPasswordException</code> without detail message.
   */
  public InvalidPasswordException() {
  }

  /**
   * Constructs an instance of <code>InvalidPasswordException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public InvalidPasswordException(String msg) {
    super(msg);
  }
}
