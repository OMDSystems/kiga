package businessLogic.zeroType;

/**
 *
 * @author Oliver
 */
public class RoomNotFoundException extends Exception {

  /**
   * Creates a new instance of <code>RoomNotFoundException</code> without detail message.
   */
  public RoomNotFoundException() {
  }

  /**
   * Constructs an instance of <code>RoomNotFoundException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public RoomNotFoundException(String msg) {
    super(msg);
  }
}
