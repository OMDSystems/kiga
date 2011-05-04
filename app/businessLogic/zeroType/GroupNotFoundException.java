/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.zeroType;

/**
 *
 * @author Marvin
 */
public class GroupNotFoundException extends Exception {

  /**
   * Creates a new instance of <code>GroupNotFoundException</code> without detail message.
   */
  public GroupNotFoundException() {
  }

  /**
   * Constructs an instance of <code>GroupNotFoundException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public GroupNotFoundException(String msg) {
    super(msg);
  }
}
