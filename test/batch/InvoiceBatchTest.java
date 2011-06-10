package batch;

import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;

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
  public void runBatch() {
    InvoiceBatch invoiceBatch = new InvoiceBatch();
    invoiceBatch.doJob();
  }
}
