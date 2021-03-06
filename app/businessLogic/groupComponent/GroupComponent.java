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
 * @author OhWeh
 */
public class GroupComponent implements IGroupmanagement {

  private static GroupComponent groupComponent = null;
  private GroupDAO crudusecase = null;

  private GroupComponent() {
    this.crudusecase = GroupDAO.createCRUDUseCase();
  }

  ;

  public static GroupComponent createComponent() {
    if (groupComponent == null) {
      groupComponent = new GroupComponent();
    }
    return groupComponent;
  }

  public long createGroup(GroupType grouptype, WeekdayType weekdaytype, double price, String name, long room) throws TechnicalProblemException, RoomNotFoundException {
    return crudusecase.createGroup(grouptype, weekdaytype, price, name, room);
  }

  public boolean deleteGroup(long id) throws TechnicalProblemException, GroupNotFoundException {
    return crudusecase.deleteGroup(id);
  }

  public IGroupData getGroupById(long id) throws GroupNotFoundException {
    return crudusecase.getGroupById(id);
  }

  public Map<WeekdayType, Map<GroupType, Map<IRoomData, List<IGroupData>>>> getAllGroups() throws TechnicalProblemException {
    return crudusecase.getAllGroups();
  }

  public void deleteAllGroups() throws GroupNotFoundException {
    crudusecase.deleteAllGroups();
  }

  public boolean updateGroup(GroupType grouptype, WeekdayType weekdaytype, long id, double price, String name, long roomId) throws TechnicalProblemException, GroupNotFoundException, RoomNotFoundException {
    return crudusecase.updateGroup(grouptype, weekdaytype, id, price, name, roomId);
  }

  public IRoomData getRoomById(long id) throws RoomNotFoundException {
    return crudusecase.getRoomById(id);
  }

  public long createRoom(String name, int capacity) {
    return crudusecase.createRoom(name, capacity);
  }

  public Collection<IRoomData> getAllRooms() throws TechnicalProblemException {
    return crudusecase.getAllRooms();
  }

  public void deleteAllRooms() {
    crudusecase.deleteAllRooms();
  }

  public void deleteRoomById(long roomId) {
    crudusecase.deleteRoomById(roomId);
  }

  public IWaitingQueueData getWaitingQueueByGroupId(long groupId) throws GroupNotFoundException {
    return crudusecase.getWaitingQueueByGroupId(groupId);
  }

  public boolean addChildToWaitingQueue(long groupId, long childId) throws GroupNotFoundException {
    return crudusecase.addChildToWaitingQueue(groupId, childId);
  }

  public boolean removeChildFromWaitingQueue(long groupId, long childId) throws GroupNotFoundException {
    return crudusecase.removeChildFromWaitingQueue(groupId, childId);
  }
}
