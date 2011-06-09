package batch;

import businessLogic.mocks.financialComponent.IInvoiceData;
import java.util.Collection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang.NotImplementedException;

/**
 * Singlton object for accessing messaging system
 * @author Marvin
 */
public class MessageProxy {
  
  

  private MessageProxy() {
  }
  
  /**
   * Returns instance of message proxy
   * @return 
   */
  public static MessageProxy getMessageProxy(){
    return MessageProxyHolder.INSTANCE;
  }
  
  /**
   * Class to ensure singleton object
   */
  private static class MessageProxyHolder {
    private static final MessageProxy INSTANCE = new MessageProxy();
  }
  
  /**
   * Sends invoice data to invoice message queue
   * @param invoiceData 
   */
  public void sendInvoicesToHamburgerKindergaerten(Collection<? extends IInvoiceData> invoiceData){
    throw new NotImplementedException("TODO");
    
  }
  
}
