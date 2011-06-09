package rightsManagementProxy;

/**
 * Exception that is thrown when an invalid username is used
 * @author Marvin
 */
public class InvalidUsernameException extends Exception {

  /**
   * Creates a new instance of <code>InvalidUsernameException</code> without detail message.
   */
  public InvalidUsernameException() {
  }

  /**
   * Constructs an instance of <code>InvalidUsernameException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public InvalidUsernameException(String msg) {
    super(msg);
  }
}
