package buildAndConfigure;

import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.RoomNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for creating different sets of testdata
 * @author Marvin
 */
public class TestDataGenerator {
    
    private static IGroupmanagement groupmanagement;
    
    private static ICustomermanagement customermanagement;
    
    private TestDataGenerator() {}
    
    public static void makeChildrenForJuerFixe4(){
      groupmanagement = BuildAndConfigureSystem.getGroupComponent();
      customermanagement = BuildAndConfigureSystem.getCustomerComponent();
      deleteAllData();
    try {
      customermanagement.createChild("Marvin", "Ede", new Date(), "none", new AdressType("Foostreet", "12345", "Hamburg", "", "26"));
      customermanagement.createChild("Oliver", "Willhoeft", new Date(), "none", new AdressType("Foostreet", "12345", "Hamburg", "", "26"));
      customermanagement.createChild("Dario", "Rexin", new Date(), "none", new AdressType("Foostreet", "12345", "Hamburg", "", "26"));
      customermanagement.createChild("Stefan", "Sarstedt", new Date(), "none", new AdressType("Foostreet", "12345", "Hamburg", "", "26"));
    } catch (TechnicalProblemException ex) {
      Logger.getLogger(TestDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
    }
            
      
    }
    
    public static void generateSet1() throws TechnicalProblemException{
        try {
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
        } catch (RoomNotFoundException ex) {
            Logger.getLogger(TestDataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void deleteAllData(){
        customermanagement.deleteAllChildren();
//        groupmanagement.deleteAllGroups();
    }
    
}
