package businessLogic.customerComponent;


import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import java.util.Collection;
import java.util.List;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.Arrays;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;
import static org.junit.Assert.*;

/**
 *
 * @author Marvin
 */
public class CustomerComponentTest extends UnitTest {


    private static ICustomermanagement customermanagement;
    
    private static IGroupmanagement groupmanagement;


    public CustomerComponentTest() {
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
     public void testCreateChild() throws TechnicalProblemException {
        long createChild = customermanagement.createChild("Hans", "Wurst", new Date(), "vegetables", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        assertTrue(createChild > 0);
     }
     
     @Test
     public void testGetChildDataSuccess() throws TechnicalProblemException{
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        IChildData childData = customermanagement.getChildData(createChild);
        assertEquals("Tom", childData.getName());
        assertEquals("Kauschat", childData.getFamilyName());
     }
     
     @Test
     public void testGetChildDataFailure() throws TechnicalProblemException{
        IChildData childData = customermanagement.getChildData(Long.MAX_VALUE);
        assertNull(childData);
     }
     
     @Test
     public void testDeleteChildSuccess() throws TechnicalProblemException{
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        boolean deleteChild = customermanagement.deleteChild(createChild);
        assertTrue(deleteChild);
     }
     
     @Test
     public void testDeleteChildFailure() throws TechnicalProblemException{
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        boolean deleteChild = customermanagement.deleteChild(Long.MAX_VALUE);
        assertFalse(deleteChild);
     }
     
     @Test
     public void testUpdateChildSuccess() throws TechnicalProblemException {
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        IChildData preChildData = customermanagement.getChildData(createChild);
        boolean updateChild = customermanagement.updateChild(createChild, preChildData.getName(), "Meyer", preChildData.getDateOfBirth(), preChildData.getAllergies(), preChildData.getAdress());
        assertEquals(1, ChildEntity.count());
        assertEquals(true, updateChild);
        IChildData postChildData = customermanagement.getChildData(createChild);
        assertEquals(preChildData.getName(), postChildData.getName());
        assertEquals(preChildData.getDateOfBirth(), postChildData.getDateOfBirth());
        assertEquals(preChildData.getAllergies(), postChildData.getAllergies());
        assertEquals(preChildData.getAdress(), postChildData.getAdress());
        assertEquals("Meyer", preChildData.getFamilyName());
        assertEquals("Meyer", postChildData.getFamilyName());
     }
     
     @Test
     public void testUpdateChildFailure() throws TechnicalProblemException {
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        IChildData preChildData = customermanagement.getChildData(createChild);
        boolean updateChild = customermanagement.updateChild(createChild+1, preChildData.getName(), "Meyer", preChildData.getDateOfBirth(), preChildData.getAllergies(), preChildData.getAdress());
        assertEquals(1, ChildEntity.count());
        assertEquals(false, updateChild);
        IChildData postChildData = customermanagement.getChildData(createChild);
        assertEquals(preChildData.getName(), postChildData.getName());
        assertEquals(preChildData.getDateOfBirth(), postChildData.getDateOfBirth());
        assertEquals(preChildData.getAllergies(), postChildData.getAllergies());
        assertEquals(preChildData.getAdress(), postChildData.getAdress());
        assertEquals("Kauschat", preChildData.getFamilyName());
        assertEquals("Kauschat", postChildData.getFamilyName());
     }
     
     @Test
     public void testDeleteAllGroups() throws TechnicalProblemException {
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        assertEquals(6, ChildEntity.count());
        customermanagement.deleteAllChildren();
        assertEquals(0, ChildEntity.count());
    }

    @Test
    public void testGetAllChildren() throws TechnicalProblemException {
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        assertEquals(6, customermanagement.getAllChildren().size());
        customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        assertEquals(7, customermanagement.getAllChildren().size());
    }
    
//    @Test
//    public void testAssignChildToGroup() throws TechnicalProblemException, GroupNotFoundException, ChildNotFoundException{
//        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
//        long updateGroup = groupmanagement.updateGroup(GroupType.EARLY, WeekdayType.MONDAY, 10d, "test", -1);
//
//        //Child is not in group
//        assertFalse(customermanagement.getChildData(createChild).getGroups().contains(updateGroup));
//
//        boolean assignChildToGroup = customermanagement.assignChildToGroup(createChild, updateGroup);
//        //Successfully added
//        assertTrue(assignChildToGroup);
//
//        //Child in group
//        assertTrue(customermanagement.getChildData(createChild).getGroups().contains(updateGroup));
//
//        boolean assignChildToGroupAgain = customermanagement.assignChildToGroup(createChild, updateGroup);
//
//        //Not added
//        assertFalse(assignChildToGroup);
//
//        //Still in group
//        assertEquals(null, customermanagement.getChildData(createChild));
//        assertTrue(customermanagement.getChildData(createChild).getGroups().contains(updateGroup));
//
//
//
//    }
    
    @Test
    public void testGetAllChildrenForGroup() throws TechnicalProblemException {
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY, 10d, "dare devils", -1);
        Collection<IChildData> allChildrenForGroup = customermanagement.getAllChildrenForGroup(createGroup);

        assertEquals(0,allChildrenForGroup.size());
    }
    

}