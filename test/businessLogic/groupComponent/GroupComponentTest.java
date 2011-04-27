/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessLogic.groupComponent;

import businessLogic.groupComponent.GroupEntity;
import businessLogic.groupComponent.IGroupData;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
//        groupmanagement.clearAll();
        GroupEntity.deleteAll();
    }

     @Test
     public void testCreateGroup() throws TechnicalProblemException {
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
        assertTrue(createGroup > 0);
        IGroupData groupData = groupmanagement.getGroupById(createGroup);
        assertEquals(groupData.getName(),"rote Teufel");
     }

     @Test
     public void testDeleteGroupSuccess() throws TechnicalProblemException{
         long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
         groupmanagement.deleteGroup(createGroup);
         assertEquals(groupmanagement.getGroupById(createGroup), null);
     }

     @Test
     public void testDeleteGroupFail() throws TechnicalProblemException{
         long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
         boolean deleteGroup = groupmanagement.deleteGroup(createGroup);
        assertFalse(groupmanagement.deleteGroup(createGroup));
     }

     @Test
     public void testGetGroupByIdSuccess() throws TechnicalProblemException{
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
        IGroupData groupData = groupmanagement.getGroupById(createGroup);
        assertEquals(GroupType.EARLY, groupData.getGroupType());
        assertEquals("rote Teufel", groupData.getName());
     }

     @Test
     public void testGetGroupByIdFail() throws TechnicalProblemException{
        long createGroup = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
        IGroupData groupData = groupmanagement.getGroupById(Long.MAX_VALUE);
        assertEquals(null, groupData);
     }

     @Test
     public void testDeleteAllGroupsSuccess() throws TechnicalProblemException{
         long early = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
         long wholeday = groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
         groupmanagement.deleteAllGroups();
         assertEquals(groupmanagement.getGroupById(early), null);
         assertEquals(groupmanagement.getGroupById(wholeday), null);
     }

//     @Test
//     public void testGetAllGroupsSuccess() throws TechnicalProblemException{
//         long early = groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY,10.00 , "rote Teufel", -1);
//         long wholeday = groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY,10.00 , "gruene Teufel", -1);
//         long late = groupmanagement.createGroup(GroupType.LATE, WeekdayType.MONDAY,10.00 , "blaue Teufel", -1);
//         long afternoon = groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.MONDAY,10.00 , "lila Teufel", -1);
//         long morning = groupmanagement.createGroup(GroupType.MORNING, WeekdayType.MONDAY,10.00 , "weisse Teufel", -1);
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
