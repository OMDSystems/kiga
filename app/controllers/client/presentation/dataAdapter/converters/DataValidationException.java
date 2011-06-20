/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.client.presentation.dataAdapter.converters;

/**
 *
 * @author Marvin
 */
public class DataValidationException extends Exception {

  /**
   * Creates a new instance of <code>DataValidationException</code> without detail message.
   */
  public DataValidationException() {
  }

  /**
   * Constructs an instance of <code>DataValidationException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public DataValidationException(String msg) {
    super(msg);
  }
}
