package batch;

import businessLogic.mocks.financialComponent.IInvoiceData;
import java.io.IOException;
import java.util.Collection;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * Singlton object for accessing messaging system
 * @author Marvin
 */
public class MessageProxy {

  /**Hostname where rabbitMQ server can be found */
  protected static final String HOSTNAME = "localhost";
  /** Name of queue for invoices */
  protected final static String QUEUE_NAME = "invoiceQueue";
  /** Connection for sending messages */
  Connection connection;
  /** Channel for sending messages */
  Channel channel;

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
  
  /** Create connection. Create session from connection; false means
   * session is not transacted. Create sender and text message. Send
   * messages, varying text slightly. Send end-of-messages message.
   * Finally, close connection.
   * @return true if established <br> false else
   */
  protected boolean establishConnection() {
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost(HOSTNAME);
      connection = factory.newConnection();
      channel = connection.createChannel();

      //Creating a new queue (idempotent) that is durable, not exclusive and has auto delete disabled
      channel.queueDeclare(QUEUE_NAME, true, false, false, null);

      return true;
    } catch (IOException ex) {
      System.err.println("Failed to establish connection of message queue:" + ex.getLocalizedMessage());
      closeConnection();
      return false;
    }

  }

  protected void sendMessages(Collection<? extends IInvoiceData> invoiceData) {
    for (IInvoiceData data : invoiceData) {
      for (int i = 0; i < 1; i++) {
        try {
          channel.basicPublish("", QUEUE_NAME, null, invoiceToBytearray(data));
        } catch (IOException ex) {
          System.err.println("Unable to send invoice no "+data.getInvoiceNumber());
        }
      }
    }
  }
  
  private byte[] invoiceToBytearray(IInvoiceData invoiceData){
    return ("invoicenumber: "+invoiceData.getInvoiceNumber()+"\n"+
            "name: "+invoiceData.getName()+"\n"+
            "lastname: "+invoiceData.getLastName()+"\n"+
            "amount: "+invoiceData.getAmountInEuro())
            .getBytes();
  }

  protected void closeConnection() {
    try {
      channel.close();
      connection.close();
    } catch (IOException ex) {
      System.err.println("Failed to close connection of message queue:" + ex.getLocalizedMessage());
    }
  }

  /**
   * Class to ensure singleton object
   */
  private static class MessageProxyHolder {

    private static final MessageProxy INSTANCE = new MessageProxy();
  }

  protected MessageProxy() {
  }

  /**
   * Returns instance of message proxy
   * @return 
   */
  public static MessageProxy getMessageProxy() {
    return MessageProxyHolder.INSTANCE;
  }
}
//package batch;
//
//import businessLogic.mocks.financialComponent.IInvoiceData;
//import java.util.Collection;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MapMessage;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
///**
// * Singlton object for accessing messaging system
// * @author Marvin
// */
//public class MessageProxy {
//  
//  private final String BROKER_URL = "http://localhost:8161/queue";
//          //ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
//
//  Context jndiContext = null;
//  ActiveMQConnectionFactory connectionFactory = null;
//  Connection connection = null;
//  Session session = null;
//  Destination destination = null;
//  MessageProducer producer = null;
//  String destinationName = "kiga.invoiceQueue";
//  int numMsgs;
//
//  private MessageProxy() {
//  }
//
//  /**
//   * Returns instance of message proxy
//   * @return 
//   */
//  public static MessageProxy getMessageProxy() {
//    return MessageProxyHolder.INSTANCE;
//  }
//
//  /** Create connection. Create session from connection; false means
//   * session is not transacted. Create sender and text message. Send
//   * messages, varying text slightly. Send end-of-messages message.
//   * Finally, close connection.
//   * @return true if established <br> false else
//   */
//  private boolean establishConnection() {
//    /*
//     * Create a JNDI API InitialContext object
//     */
//    try {
//      jndiContext = new InitialContext();
////      System.out.println("jndiContext = " + jndiContext);
//    } catch (NamingException e) {
//      System.err.println("Failed to initialize context of messaging connector");
//      return false;
//    }
//
//    /*
//     * Look up connection factory and destination.
//     */
//    try {
////      connectionFactory =  (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
//      connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, BROKER_URL);
//      System.out.println("connectionFactory = " + connectionFactory);
//      connection = connectionFactory.createQueueConnection();
//      System.out.println("connection = " + connection);
////      connection.start();
//      session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//      session.createQueue(destinationName + "2");
//      System.out.println("DESTINATION IS BEING LOOKED UP");
//      destination = (Destination) jndiContext.lookup(destinationName);
//      System.out.println("destination = " + destination);
//      System.out.println("DESTINATION WAS BEING LOOKED UP");
//      producer = session.createProducer(destination);
//    } catch (JMSException ex) {
//      System.err.println("JMS exception thrown: " + ex.getMessage());
//      return false;
//    } catch (NamingException e) {
//      System.err.println("Could not find connection name");
//      return false;
//    }
//    return true;
//
//  }
//
//  private void sendMessages(Collection<? extends IInvoiceData> invoiceData) {
//    for (IInvoiceData data : invoiceData) {
//      try {
//        MapMessage message = session.createMapMessage();
//        message.setDoubleProperty("amount", data.getAmountInEuro());
//        message.setLongProperty("invoice number", data.getInvoiceNumber());
//        message.setStringProperty("name", data.getName());
//        message.setStringProperty("last name", data.getLastName());
//        producer.send(message);
//      } catch (JMSException ex) {
//        Logger.getLogger(MessageProxy.class.getName()).log(Level.SEVERE, null, ex);
//      }
//    }
//  }
//
//  private void closeConnection() {
//    try {
//      /*
//       * Send a non-text control message indicating end of messages.
//       */
//      producer.send(session.createMessage());
//    } catch (JMSException e) {
//      System.err.println("Could not send control message");
//    } finally {
//      if (connection != null) {
//        try {
//          connection.close();
//        } catch (JMSException e) {
//        }
//      }
//    }
//  }
//
//  /**
//   * Class to ensure singleton object
//   */
//  private static class MessageProxyHolder {
//
//    private static final MessageProxy INSTANCE = new MessageProxy();
//  }
//
//  /**
//   * Sends invoice data to invoice message queue
//   * @param invoiceData 
//   */
//  public void sendInvoicesToHamburgerKindergaerten(Collection<? extends IInvoiceData> invoiceData) {
//    if (establishConnection()) {
//      sendMessages(invoiceData);
//    }
//    closeConnection();
//
//  }
//}
