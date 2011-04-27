/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic.groupComponent;

import businessLogic.groupComponent.GroupEntity;
import businessLogic.groupComponent.IGroupData;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.RoomNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.UnitTest;

/**
 *
 * @author OhWeh
 */
public class GroupComponentTest extends UnitTest {


    private static IGroupmanagement groupmanagement;


    public GroupComponentTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        buildAndConfigure.BuildAndConfigureSystem.buildAndConfigureSystem();
        groupmanagement = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    long testroom;
    @Before
    public void setUp() {
       testroom = groupmanagement.createRoom("blau", 10);

    }

    @After
    public void tearDown() {
//        groupmanagement.clearAll();
        GroupEntity.deleteAll();
    }

    @Test
    public void testCreateRoom() throws RoomNotFoundException{
        long room = groupmanagement.createRoom("10.01", 18);
        assertTrue(room > 0);
        IRoomData roomData = groupmanagement.getRoomById(room);
        assertEquals("10.01", roomData.getName());
    }

     @Test
     public void testCreateGroup() throws TechnicalProblemException, GroupNotFoundException, RoomNotFoundException {
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
        assertTrue(createGroup > 0);
        IGroupData groupData = groupmanagement.getGroupById(createGroup);
        assertEquals(groupData.getName(),"rote Teufel");
     }

     @Test
     public void testDeleteGroupSuccess() throws TechnicalProblemException, RoomNotFoundException{
         long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
         groupmanagement.deleteGroup(createGroup);
        try {
            groupmanagement.getGroupById(createGroup);
            fail("Sollte ein Fehler geworfen werden");
        } catch (GroupNotFoundException ex) {
        }
     }

     @Test
     public void testDeleteGroupFail() throws TechnicalProblemException, RoomNotFoundException{
         long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
         boolean deleteGroup = groupmanagement.deleteGroup(createGroup);
        assertFalse(groupmanagement.deleteGroup(createGroup));
     }

     @Test
     public void testGetGroupByIdSuccess() throws TechnicalProblemException, GroupNotFoundException, RoomNotFoundException{
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
        IGroupData groupData = groupmanagement.getGroupById(createGroup);
        assertEquals(GroupType.EARLY, groupData.getGroupType());
        assertEquals("rote Teufel", groupData.getName());
     }

     @Test
     public void testGetGroupByIdFail() throws TechnicalProblemException, RoomNotFoundException{
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
          try {
            groupmanagement.getGroupById(Long.MAX_VALUE);
            fail("Sollte ein Fehler geworfen werden");
        } catch (GroupNotFoundException ex) {
        }
     }

//     @Test
//     public void testDeleteAllGroupsSuccess() throws TechnicalProblemException, GroupNotFoundException, RoomNotFoundException{
//         long early = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
//         long wholeday = groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
//         groupmanagement.deleteAllGroups();
//         assertEquals(groupmanagement.getGroupById(early), null);
//         assertEquals(groupmanagement.getGroupById(wholeday), null);
//     }

//     @Test
//     public void testGetAllGroupsSuccess() throws TechnicalProblemException{
//         long early = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
//         long wholeday = groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY,10.00 , "gruene Teufel", testroom);
//         long late = groupmanagement.createGroup(GroupType.LATE, WeekdayType.MONDAY,10.00 , "blaue Teufel", testroom);
//         long afternoon = groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.MONDAY,10.00 , "lila Teufel", testroom);
//         long morning = groupmanagement.createGroup(GroupType.MORNING, WeekdayType.MONDAY,10.00 , "weisse Teufel", testroom);
//
//         Map<WeekdayType, Map<GroupType,IGroupData>> groups = groupmanagement.getAllGroups();
//
//         assertTrue(Long.valueOf(groups.get(WeekdayType.MONDAY).get(GroupType.EARLY).getGroupId()).equals(Long.valueOf(early)));
//         assertTrue(Long.valueOf(groups.get(WeekdayType.MONDAY).get(GroupType.LATE).getGroupId()).equals(Long.valueOf(late)));
//         assertTrue(Long.valueOf(groups.get(WeekdayType.MONDAY).get(GroupType.MORNING).getGroupId()).equals(Long.valueOf(morning)));
//         assertTrue(Long.valueOf(groups.get(WeekdayType.MONDAY).get(GroupType.AFTERNOON).getGroupId()).equals(Long.valueOf(afternoon)));
//         assertTrue(Long.valueOf(groups.get(WeekdayType.MONDAY).get(GroupType.WHOLEDAY).getGroupId()).equals(Long.valueOf(wholeday)));
//         assertEquals("", groups.get(WeekdayType.THURSDAY).get(GroupType.WHOLEDAY).getName());
//     }

     

}