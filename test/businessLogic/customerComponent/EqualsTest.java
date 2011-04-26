package businessLogic.customerComponent;

import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;

/**
 *
 * @author Marvin
 */
public class EqualsTest extends UnitTest {


    private static ICustomermanagement customermanagement;

    private static IGroupmanagement groupmanagement;


    public EqualsTest() {
    }

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
    public void setUp() {
    }

    @After
    public void tearDown() {
//        groupmanagement.deleteAllGroups();
        customermanagement.deleteAllChildren();
    }

     @Test
     public void testEqualsForModels() throws TechnicalProblemException{
        ChildEntity childEntity = new ChildEntity("olli", "w.", new Date(), "keine", new AdressType("Augenjuckstraße", "21111", "Hamburg", "", "17b"));
        ChildEntity childEntity1 = new ChildEntity("olli", "w.", new Date(), "keine", new AdressType("Augenjuckstraße", "21111", "Hamburg", "", "17b"));

        assertEquals(childEntity,childEntity1);
     }


}