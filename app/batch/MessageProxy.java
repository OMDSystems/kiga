package batch;

import businessLogic.mocks.financialComponent.IInvoiceData;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Singlton object for accessing messaging system
 * @author Marvin
 */
public class MessageProxy {

  Context jndiContext = null;
  ActiveMQConnectionFactory connectionFactory = null;
  Connection connection = null;
  Session session = null;
  Destination destination = null;
  MessageProducer producer = null;
  String destinationName = "kiga.invoiceQueue";
  int numMsgs;

  private MessageProxy() {
  }

  /**
   * Returns instance of message proxy
   * @return 
   */
  public static MessageProxy getMessageProxy() {
    return MessageProxyHolder.INSTANCE;
  }

  /** Create connection. Create session from connection; false means
   * session is not transacted. Create sender and text message. Send
   * messages, varying text slightly. Send end-of-messages message.
   * Finally, close connection.
   * @return true if established <br> false else
   */
  private boolean establishConnection() {
    /*
     * Create a JNDI API InitialContext object
     */
    try {
      jndiContext = new InitialContext();
//      System.out.println("jndiContext = " + jndiContext);
    } catch (NamingException e) {
      System.err.println("Failed to initialize context of messaging connector");
      return false;
    }

    /*
     * Look up connection factory and destination.
     */
    try {
//      connectionFactory =  (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
      connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
      connection = connectionFactory.createQueueConnection();
//      connection.start();
      session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
      session.createQueue(destinationName + "2");
      System.out.println("DESTINATION IS BEING LOOKED UP");
      destination = (Destination) jndiContext.lookup(destinationName);
      System.out.println("DESTINATION WAS BEING LOOKED UP");
      producer = session.createProducer(destination);
    } catch (JMSException ex) {
      System.err.println("JMS exception thrown: " + ex.getMessage());
      return false;
    } catch (NamingException e) {
      System.err.println("Could not find connection name");
      return false;
    }
    return true;

  }

  private void sendMessages(Collection<? extends IInvoiceData> invoiceData) {
    for (IInvoiceData data : invoiceData) {
      try {
        MapMessage message = session.createMapMessage();
        message.setDoubleProperty("amount", data.getAmountInEuro());
        message.setLongProperty("invoice number", data.getInvoiceNumber());
        message.setStringProperty("name", data.getName());
        message.setStringProperty("last name", data.getLastName());
        producer.send(message);
      } catch (JMSException ex) {
        Logger.getLogger(MessageProxy.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private void closeConnection() {
    try {
      /*
       * Send a non-text control message indicating end of messages.
       */
      producer.send(session.createMessage());
    } catch (JMSException e) {
      System.err.println("Could not send control message");
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
        }
      }
    }
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
  public void sendInvoicesToHamburgerKindergaerten(Collection<? extends IInvoiceData> invoiceData) {
    if (establishConnection()) {
      sendMessages(invoiceData);
    }
    closeConnection();

  }
}
