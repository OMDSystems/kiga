/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import businessLogic.mocks.financialComponent.IInvoiceData;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.crypto.Data;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.On;

/**
 *
 * @author Marvin
 */
//@Every("5s")
@On("59 59 23 * * ?") // This means "every day at 1 second before midnight"; We manualy check if its the last day of month
public class InvoiceBatch extends Job{
  
  /** Force batch to be run whenever method is called (for testing) */
  private boolean alwaysRunBatch = false;
  
  private final MessageProxy messageProxy;

  public InvoiceBatch() {
    super();
    messageProxy = MessageProxy.getMessageProxy();
  }
  
  

  @Override
  public void doJob(){
    if (todayIsLastDayOfMonth()) {
      sendInvoicesToHamburgerKindergaerten();
    }
  }

  private boolean todayIsLastDayOfMonth() {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    int lastDayOfMonth = gregorianCalendar.getActualMaximum(Calendar.DATE);
    return Calendar.DAY_OF_MONTH == lastDayOfMonth || alwaysRunBatch;
  }

  private void sendInvoicesToHamburgerKindergaerten() {
    try {
       Collection<IInvoiceData> invoicesOfCurrentMonth = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent().getInvoicesOfCurrentMonth();
       messageProxy.sendInvoicesToHamburgerKindergaerten(invoicesOfCurrentMonth);
    } catch (TechnicalProblemException ex) {
      //TODO: Write mail to admin
      //TODO: dont panic
      Logger.getLogger(InvoiceBatch.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
}
