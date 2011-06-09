/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.mocks.financialComponent;

import businessLogic.customerComponent.IChildData;
import java.util.List;

/**
 *
 * @author Marvin
 */
public interface IInvoiceData {
  
  Long getInvoiceNumber();
  
  String getName();
  
  String getLastName();
  
  Double getAmountInEuro();
  
}
