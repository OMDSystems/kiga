
package businessLogic.groupComponent;

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

/**
 *
 * @author Oliver
 */
public class CRUDUseCase {

    private static CRUDUseCase usecase = null;

    private CRUDUseCase(){

    }

    /**
     * Creates the CRUDUseCase
     * @return
     */
    public static CRUDUseCase createCRUDUseCase(){
        if (usecase == null) {
            usecase = new CRUDUseCase();
        }
        return usecase;
    }


    boolean deleteGroup(long id) throws GroupNotFoundException {
       if (GroupEntity.findById(id)==null) {
            return false;
        }else{
            ((GroupEntity)GroupEntity.findById(id)).delete();
            ((WaitingQueueEntity)WaitingQueueEntity.findById(getWaitingQueueIdByRoomId(id))).delete();
            return GroupEntity.findById(id) == null;
        }
    }


    IGroupData getGroupById(long id) throws GroupNotFoundException {
        if(GroupEntity.findById(id) == null){
            throw new GroupNotFoundException("Group with id "+id+" can not be found");
        }
        return (IGroupData)GroupEntity.findById(id);
    }

    void deleteAllGroups() throws GroupNotFoundException {
        List<GroupEntity> groups = GroupEntity.findAll();
        for (GroupEntity groupEntity : groups) {
            deleteGroup(groupEntity.getId());
        }
    }

    Map<WeekdayType, Map<GroupType,Map<IRoomData, List<IGroupData>>>> getAllGroups() throws TechnicalProblemException {
        Map<WeekdayType, Map<GroupType,Map<IRoomData, List<IGroupData>>>> result = new HashMap<WeekdayType, Map<GroupType,Map<IRoomData, List<IGroupData>>>>();
        List<RoomEntity> rooms = RoomEntity.findAll();
        for (WeekdayType weekday : WeekdayType.values()) {
            Map<GroupType, Map<IRoomData, List<IGroupData>>> dayColumn = new HashMap<GroupType, Map<IRoomData, List<IGroupData>>>();
            for (GroupType grouptype : GroupType.values()) {
                Map<IRoomData, List<IGroupData>> roomColumn = new HashMap<IRoomData, List<IGroupData>>();
                for(RoomEntity room : rooms){
                    roomColumn.put((IRoomData)room, new ArrayList());
                }
                dayColumn.put(grouptype, roomColumn);
            }
            result.put(weekday, dayColumn);
        }
        
        List<GroupEntity> groups = GroupEntity.findAll();

        for (GroupEntity groupEntity : groups) {
            List temp = result.get(groupEntity.getWeekdayType()).get(groupEntity.getGroupType()).get(groupEntity.getRoom());
            temp.add(groupEntity);
        }

        return result;
    }

    boolean updateGroup(GroupType grouptype, WeekdayType weekdaytype,long id,double price, String name, long roomId) throws GroupNotFoundException, RoomNotFoundException {
           if (GroupEntity.findById(id) !=null) {
            RoomEntity room = (RoomEntity)getRoomById(roomId);
            GroupEntity group = (GroupEntity)getGroupById(id);
            group.setGrouptype(grouptype);
            group.setWeekdaytype(weekdaytype);
            group.setPrice(price);
            group.setName(name);
            group.setRoom(room);
            group.save();
            return true;
        } else {
            return false;
        }
    }

     long createGroup(GroupType groupType, WeekdayType weekdayType, double price, String name, long roomId) throws RoomNotFoundException, TechnicalProblemException {
        RoomEntity room = (RoomEntity)getRoomById(roomId);
        GroupEntity group = new GroupEntity(groupType, weekdayType, price, name, room);
        group.save();
        createWaitingQueue(group);
        return group.getId();
     }

     void createWaitingQueue(GroupEntity group){
        WaitingQueueEntity waitingQueueEntity = new WaitingQueueEntity(group);
        waitingQueueEntity.save();
     }

    IRoomData getRoomById(long id) throws RoomNotFoundException {
        if(RoomEntity.findById(id) == null){
            throw new RoomNotFoundException("Room with id "+id+" can not be found");
        }
        return (IRoomData)RoomEntity.findById(id);
    }

    long createRoom(String name, int capacity) {
        RoomEntity room = new RoomEntity(name, capacity);
        room.save();
        return room.getId();
    }

    Collection<IRoomData> getAllRooms() throws TechnicalProblemException {
        List<RoomEntity> rooms = RoomEntity.findAll();
        List<IRoomData> result = new ArrayList<IRoomData>();
        for (RoomEntity roomEntity : rooms) {
            result.add((IRoomData)roomEntity);
        }
        return result;
    }

    void deleteRoomById(long roomId){
        ((RoomEntity)RoomEntity.findById(roomId)).delete();
    }

    void deleteAllRooms() {
        List<RoomEntity> rooms = RoomEntity.findAll();
        for (RoomEntity roomEntity : rooms) {
            deleteRoomById(roomEntity.getId());
        }
    }

    IWaitingQueueData getWaitingQueueByRoomId(long groupId) throws GroupNotFoundException  {
        getGroupById(groupId);
        List<WaitingQueueEntity> waitingqueues = WaitingQueueEntity.findAll();
        for (WaitingQueueEntity waitingQueueEntity : waitingqueues) {
            if(waitingQueueEntity.getGroupId() == groupId){
                return (IWaitingQueueData)WaitingQueueEntity.findById(waitingQueueEntity.getWaitingQueueId());
            }
        }
            return null;
    }

    private long getWaitingQueueIdByRoomId(long groupId) throws GroupNotFoundException{
        getGroupById(groupId);
        List<WaitingQueueEntity> waitingqueues = WaitingQueueEntity.findAll();
        for (WaitingQueueEntity waitingQueueEntity : waitingqueues) {
            if(waitingQueueEntity.getGroupId() == groupId){
                return waitingQueueEntity.getWaitingQueueId();
            }
        }
            return -1;
    }

}
