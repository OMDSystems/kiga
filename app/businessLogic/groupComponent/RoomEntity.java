package businessLogic.groupComponent;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * Entity of room
 * @author Oliver
 */
@Entity
class RoomEntity extends Model implements IRoomData {

  private String name;
  private int capacity;

  public RoomEntity(String name, int capacity) {
    this.name = name;
    this.capacity = capacity;
  }

  public long getRoomId() {
    return this.getId();
  }

  public String getName() {
    return name;
  }

  public int getCapacity() {
    return capacity;
  }

  void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  void setName(String name) {
    this.name = name;
  }
}
