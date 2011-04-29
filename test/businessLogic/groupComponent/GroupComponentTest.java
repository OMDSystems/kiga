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
import java.util.Collection;
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

     @Test
     public void testDeleteAllGroupsSuccess() throws TechnicalProblemException, GroupNotFoundException, RoomNotFoundException{
         long early = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
         long wholeday = groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
         groupmanagement.deleteAllGroups();
            try {
            groupmanagement.getGroupById(early);
            groupmanagement.getGroupById(wholeday);
            fail("Sollte ein Fehler geworfen werden");
        } catch (GroupNotFoundException ex) {
        }
     }

     @Test
     public void testGetAllGroupsSuccess() throws TechnicalProblemException, RoomNotFoundException, GroupNotFoundException{
         long room = groupmanagement.createRoom("grün", 5);
         long early = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", testroom);
         long wholeday = groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY,10.00 , "gruene Teufel", room);
         long late = groupmanagement.createGroup(GroupType.LATE, WeekdayType.MONDAY,10.00 , "blaue Teufel", testroom);
         long afternoon = groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.MONDAY,10.00 , "lila Teufel", testroom);

         Map<WeekdayType, Map<GroupType,Map<IRoomData, List<IGroupData>>>> groups = groupmanagement.getAllGroups();

         List<IGroupData> roomsMonday = groups.get(WeekdayType.MONDAY).get(GroupType.EARLY).get(groupmanagement.getRoomById(testroom));
         List<IGroupData> roomsWholeday = groups.get(WeekdayType.MONDAY).get(GroupType.WHOLEDAY).get(groupmanagement.getRoomById(room));
         List<IGroupData> roomsLate = groups.get(WeekdayType.MONDAY).get(GroupType.LATE).get(groupmanagement.getRoomById(testroom));
         List<IGroupData> roomsAfternoon = groups.get(WeekdayType.MONDAY).get(GroupType.LATE).get(groupmanagement.getRoomById(testroom));

         assertTrue(roomsMonday.contains(groupmanagement.getGroupById(early)));
         assertTrue(roomsWholeday.contains(groupmanagement.getGroupById(wholeday)));
         assertTrue(roomsLate.contains(groupmanagement.getGroupById(late)));
         assertFalse(roomsLate.contains(groupmanagement.getGroupById(afternoon)));

     }

      @Test
     public void testDeleteAllRoomsSuccess() throws TechnicalProblemException{
         long room1 = groupmanagement.createRoom("blau", 10);
         long room2 = groupmanagement.createRoom("grün", 12);
         groupmanagement.deleteAllRooms();
             try {
            groupmanagement.getRoomById(room1);
            groupmanagement.getRoomById(room2);
            fail("Sollte ein Fehler geworfen werden");
        } catch (RoomNotFoundException ex) {
        }
         
     }

     @Test
     public void testGetAllRooms() throws TechnicalProblemException{
         groupmanagement.deleteAllRooms();
         groupmanagement.createRoom("blau", 10);
         groupmanagement.createRoom("grün", 12);
         Collection<IRoomData> rooms = groupmanagement.getAllRooms();
         assertTrue(2 == rooms.size());
     }

     

}
