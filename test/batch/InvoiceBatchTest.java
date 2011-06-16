package batch;

import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import com.rabbitmq.client.ConsumerCancelledException;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.util.Date;

/**
 *
 * @author Marvin
 */
public class InvoiceBatchTest extends UnitTest {

  private static ICustomermanagement customermanagement;
  private static IGroupmanagement groupmanagement;

  @BeforeClass
  public static void setUpClass() throws Exception {
    buildAndConfigure.BuildAndConfigureSystem.buildAndConfigureSystem();
    customermanagement = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent();
    groupmanagement = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent();
  }

  @Test
  public void runBatch() throws IOException, ConsumerCancelledException, InterruptedException {
    InvoiceBatch invoiceBatch = new InvoiceBatch();
    invoiceBatch.doJob();
    Consumer consumer = new Consumer();
    consumer.readMessages();
  }

  private class Consumer extends MessageProxy{


    void readMessages() throws IOException, ConsumerCancelledException, InterruptedException{
      if (establishConnection()) {
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
          QueueingConsumer.Delivery delivery = consumer.nextDelivery();
          String message = new String(delivery.getBody());
          System.out.println("Message has been send at "+new Date()+":\n"+message+"\n");
        }
        
      }
      closeConnection();
      
    }
  }
}
