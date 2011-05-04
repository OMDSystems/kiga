package businessLogic.groupComponent;

import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.WeekdayType;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * Entity of Group
 * @author Oliver
 */
@Entity
public class GroupEntity extends Model implements IGroupData {

  private GroupType grouptype;
  private WeekdayType weekdaytype;
  private double price;
  private String name;
  private RoomEntity room;
  private WaitingQueueEntity queue;

  /**
   * create a group
   * @param grouptype
   * @param weekdaytype
   * @param price
   * @param name
   * @param room
   */
  public GroupEntity(GroupType grouptype, WeekdayType weekdaytype,
          double price, String name, RoomEntity room, WaitingQueueEntity waitingQueue) {
    this.grouptype = grouptype;
    this.weekdaytype = weekdaytype;
    this.price = price;
    this.name = name;
    this.room = room;
    this.queue = waitingQueue;
  }

  void setGrouptype(GroupType grouptype) {
    this.grouptype = grouptype;
  }

  void setWeekdaytype(WeekdayType weekdaytype) {
    this.weekdaytype = weekdaytype;
  }

  public GroupType getGroupType() {
    return grouptype;
  }

  public WeekdayType getWeekdayType() {
    return weekdaytype;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  void setPrice(double price) {
    this.price = price;
  }

  void setName(String name) {
    this.name = name;
  }

  public long getGroupId() {
    return this.getId();
  }

  public long getRoomId() {
    return room.getId();
  }

  void setRoom(RoomEntity room) {
    this.room = room;
  }

  public IRoomData getRoom() {
    return (IRoomData) room;
  }

  IWaitingQueueData getWaitingQueue() {
    return (IWaitingQueueData) queue;
  }
}
