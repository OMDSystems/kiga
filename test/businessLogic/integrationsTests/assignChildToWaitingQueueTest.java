package businessLogic.integrationsTests;

import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.groupComponent.IWaitingQueueData;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.RoomNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;


/**
 *
 * @author Oliver
 */
public class assignChildToWaitingQueueTest extends UnitTest {

    private static ICustomermanagement customermanagement;
    private static IGroupmanagement groupmanagement;

    @BeforeClass
    public static void setUpClass() throws Exception {
        buildAndConfigure.BuildAndConfigureSystem.buildAndConfigureSystem();
        customermanagement = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent();
        groupmanagement = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws TechnicalProblemException, RoomNotFoundException {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAssignChildToWaitingQueueSuccess() throws TechnicalProblemException, RoomNotFoundException, GroupNotFoundException{
       long testroom = groupmanagement.createRoom("blau", 10);
       long testgroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY, 10.00, "Teufelkerle", testroom);
       AdressType address = new AdressType("Stiftstr", "20558", "HH", "", "7");
       long testChild = customermanagement.createChild("Franz", "Egon", new Date(), "Keine", address);

       assertTrue(groupmanagement.addChildToWaitingQueue(testgroup, testChild));

    }

      public void testGetAllChildIdOfWaitingQueueSuccess() throws TechnicalProblemException, RoomNotFoundException, GroupNotFoundException{
       long testroom = groupmanagement.createRoom("blau", 10);
       long testgroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY, 10.00, "Teufelkerle", testroom);
       AdressType address = new AdressType("Stiftstr", "20558", "HH", "", "7");
       long testChild = customermanagement.createChild("Franz", "Egon", new Date(), "Keine", address);
       long testChild1 = customermanagement.createChild("Franzine", "Egon", new Date(), "Keine", address);

       groupmanagement.addChildToWaitingQueue(testgroup, testChild);
       groupmanagement.addChildToWaitingQueue(testgroup, testChild1);

       IWaitingQueueData queue = groupmanagement.getWaitingQueueByGroupId(testgroup);
       List<Long> childInWaitingQueue = queue.getChildInWaitingQueue();

       assertTrue(childInWaitingQueue.size() == 2 );
       assertTrue(childInWaitingQueue.contains(testChild1) );

    }

}
