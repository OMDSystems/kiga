package businessLogic.groupComponent;

import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oliver
 */
public interface IGroupmanagement {


    /**
     * create Group and Save on Database
     * @param id of the group
     * @param grouptype of the Group
     * @param weekdaytype day of the Week
     * @param price of the group
     * @param name of the group
     * @param room id of the room
     * @return <code>id</code> of the group
     *         <code> -1 </code> for failure
     * @throws TechnicalProblemException for Database failure
     */
   public boolean updateGroup(long id, double price, String name) throws TechnicalProblemException;

   /**
     * create Group and Save on Database
     * @param grouptype of the Group
     * @param weekdaytype day of the Week
     * @param price of the group
     * @param name of the group
     * @param room id of the room
     * @return <code>id</code> of the group
     *         <code> -1 </code> for failure
     * @throws TechnicalProblemException for Database failure
     */
   public long createGroup(GroupType grouptype, WeekdayType weekdaytype, double price, String name, long room ) throws TechnicalProblemException;


   /**
    * delete group - not delete from database.
    * set params empty
    * @param id of the group
    * @return <code> true </code> if deleted else <br> <code> false</code>
    * @throws TechnicalProblemException for Database failure
    */
   public boolean deleteGroup(long id) throws TechnicalProblemException;

   /**
    *
    * @param id of the group
    * @return Data of the group or <c> null </c> if not exists
    * @throws TechnicalProblemException for Database failure
    */
   public IGroupData getGroupById(long id) throws TechnicalProblemException;

   /**
    *
    * @return Map with Weekday as Key and sortet List order is: EARLY, MORNING, AFTERNOON, WHOLEDAY, LATE
    * @throws TechnicalProblemException
    */
   public Map<WeekdayType, Map<GroupType,IGroupData>> getAllGroups() throws TechnicalProblemException;

//   /**
//    * only for testing, not for View
//    */
//   public void deleteAllGroups();

   /**
    * clear all Groupdata
    */
    public void clearAll();

}
