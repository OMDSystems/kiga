package businessLogic.integrationsTests;

import businessLogic.customerComponent.IChildData;
import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.RoomNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.Collection;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;

/**
 *
 * @author Marvin
 */
public class assignChildToGroupTest extends UnitTest {

    private static ICustomermanagement customermanagement;
    private static IGroupmanagement groupmanagement;

    @BeforeClass
    public static void setUpClass() throws Exception {
        buildAndConfigure.BuildAndConfigureSystem.buildAndConfigureSystem();
        customermanagement = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent();
        groupmanagement = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent();
    }

    @Test
    public void testAssignChildToGroup() throws TechnicalProblemException, GroupNotFoundException, ChildNotFoundException, RoomNotFoundException{
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        long createRoom = groupmanagement.createRoom("11.01a", 12);
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY, 10d, "dare devils", createRoom);
//        long updateGroup = groupmanagement.updateGroup(GroupType.EARLY, WeekdayType.MONDAY, createChild, 10d, null, createChild)

        //Child is not in group
        assertFalse(customermanagement.getChildData(createChild).getGroups().contains(createGroup));

        boolean assignChildToGroup = customermanagement.assignChildToGroup(createChild, createGroup);
        //Successfully added
        assertTrue(assignChildToGroup);

        //Child in group
        assertTrue(customermanagement.getChildData(createChild).getGroups().contains(createGroup));

        boolean assignChildToGroupAgain = customermanagement.assignChildToGroup(createChild, createGroup);

        //Not added
        assertFalse(assignChildToGroup);

        //Still in group
        assertTrue(customermanagement.getChildData(createChild).getGroups().contains(createGroup));
//        assertEquals(null, customermanagement.getChildData(createChild));



    }

    @Test
    public void testGetAllChildrenForGroup() throws TechnicalProblemException, RoomNotFoundException, GroupNotFoundException {
        long createChild = customermanagement.createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
        long createRoom = groupmanagement.createRoom("11.01a", 12);
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY, 10d, "dare devils", createRoom);

        
        Collection<IChildData> allChildrenForGroup = customermanagement.getAllChildrenForGroup(createGroup);

        assertEquals(0,allChildrenForGroup.size());
    }

}
