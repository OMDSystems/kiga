/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.mocks.financialComponent;

import businessLogic.zeroType.TechnicalProblemException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marvin
 */
public class MockUseCase {
  
  public static Collection<IInvoiceData> getInvoicesOfCurrentMonth() throws TechnicalProblemException {
    
    Set<IInvoiceData> result = new HashSet<IInvoiceData>();
    
    result.add(new InvoiceEntity(1l, "Marvin", "Ede", 47.11d));
    result.add(new InvoiceEntity(2l, "Oliver", "Willhoeft", 471.1d));
    result.add(new InvoiceEntity(3l, "Dario", "Rexin", 4.71d));
    result.add(new InvoiceEntity(4l, "Stefan", "Sarstedt", 4711d));
    
    return result;
    
  }
  
}
