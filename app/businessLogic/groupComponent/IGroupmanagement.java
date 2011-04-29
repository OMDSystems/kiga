package businessLogic.groupComponent;

import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.RoomNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.Collection;
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
   public boolean updateGroup(GroupType grouptype, WeekdayType weekdaytype, long id, double price, String name, long roomId) throws TechnicalProblemException, GroupNotFoundException, RoomNotFoundException;

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
   public long createGroup(GroupType grouptype, WeekdayType weekdaytype, double price, String name, long room ) throws TechnicalProblemException, RoomNotFoundException;

   /**
    * create room and save it on database
    * @param name of the room
    * @param capacity of the room
    * @return id of the room
    */
   public long createRoom(String name, int capacity);

   /**
    * delete group - not delete from database.
    * set params empty
    * @param id of the group
    * @return <code> true </code> if deleted else <br> <code> false</code>
    * @throws TechnicalProblemException for Database failure
    */
   public boolean deleteGroup(long id) throws TechnicalProblemException, GroupNotFoundException;

   /**
    *
    * @param id of the group
    * @return Data of the group or  
    * @throws GroupNotFoundException if Group not exists
    */
   public IGroupData getGroupById(long id) throws GroupNotFoundException;

   /**
    *
    * @param id of the room
    * @return Data of the room
    * @throws RoomNotFoundException if Room not found
    */
   public IRoomData getRoomById(long id) throws RoomNotFoundException;

   /**
    *
    * @return Map with Weekday as Key, Grouptype as 2nd key and room as 3rd key
    * get Collection with all Groups at that Time and room. Empty List, if no
    * group planed
    * @throws TechnicalProblemException
    */
   public Map<WeekdayType, Map<GroupType,Map<IRoomData, List<IGroupData>>>> getAllGroups() throws TechnicalProblemException;

   /**
    * 
    * @return Collection of all rooms
    */
   public Collection<IRoomData> getAllRooms() throws TechnicalProblemException;

   /**
    * only for testing
    */
   public void deleteAllGroups() throws GroupNotFoundException;

   /**
    * only for testing
    */
   public void deleteAllRooms();

   /**
    * only for testing
    * @param roomId
    */
   public void deleteRoomById(long roomId);


   /**
    *
    * @param roomId Id of the room
    * @return Data of the WaitingQueue
    */
   public IWaitingQueueData getWaitingQueueByGroupId(long roomId) throws GroupNotFoundException;

   /**
    * Add child to Waiting queue
    * @param childId Id of the child
    * @param groupId Id of the group where child will be added
    * @return <C>true if child</c> is added, else <c>false</c>
    */
   public boolean addChildToWaitingQueue(long groupId, long childId) throws GroupNotFoundException;
   
}
