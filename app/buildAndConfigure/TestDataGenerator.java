package buildAndConfigure;

import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.Date;

/**
 * Utility class for creating different sets of testdata
 * @author Marvin
 */
public class TestDataGenerator {
    
    private static IGroupmanagement groupmanagement;
    
    private static ICustomermanagement customermanagement;
    
    private TestDataGenerator() {}
    
    public static void generateSet1() throws TechnicalProblemException{
        
        groupmanagement = BuildAndConfigureSystem.getGroupComponent();
        
        customermanagement = BuildAndConfigureSystem.getCustomerComponent();
        
        deleteAllData();
        
        groupmanagement.createGroup(GroupType.EARLY, WeekdayType.MONDAY, 10d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.MORNING, WeekdayType.MONDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.MONDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.LATE, WeekdayType.MONDAY, 15d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.MONDAY, 14.6d, "Plush Panda", -1);
        
        groupmanagement.createGroup(GroupType.EARLY, WeekdayType.TUESDAY, 10d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.MORNING, WeekdayType.TUESDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.TUESDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.LATE, WeekdayType.TUESDAY, 15d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.TUESDAY, 14.6d, "Plush Panda", -1);
        
        groupmanagement.createGroup(GroupType.EARLY, WeekdayType.WEDNESDAY, 10d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.MORNING, WeekdayType.WEDNESDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.WEDNESDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.LATE, WeekdayType.WEDNESDAY, 15d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.WEDNESDAY, 14.6d, "Plush Panda", -1);
        
        groupmanagement.createGroup(GroupType.EARLY, WeekdayType.THURSDAY, 10d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.MORNING, WeekdayType.THURSDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.THURSDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.LATE, WeekdayType.THURSDAY, 15d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.THURSDAY, 14.6d, "Plush Panda", -1);
        
        groupmanagement.createGroup(GroupType.EARLY, WeekdayType.FRIDAY, 10d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.MORNING, WeekdayType.FRIDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.AFTERNOON, WeekdayType.FRIDAY, 7.3d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.LATE, WeekdayType.FRIDAY, 20d, "Plush Panda", -1);
        groupmanagement.createGroup(GroupType.WHOLEDAY, WeekdayType.FRIDAY, 14.6d, "Plush Panda", -1);
        
        
        customermanagement.createChild("Thomas", "Gottschalk", new Date(), "Milchschnitte", new AdressType("Am Berliner Tor", "21035", "Hamburg", "11.01a", "BT7"));
        customermanagement.createChild("Eva", "Sarstedt", new Date(), "", new AdressType("Lange Hegge", "21035", "Hamburg", "", "4711"));
        
    }
    
    private static void deleteAllData(){
        customermanagement.deleteAllChildren();
//        groupmanagement.deleteAllGroups();
    }
    
}
