package businessLogic.groupComponent;

/**
 *  Interface for roomdata
 * @author Oliver
 */
public interface IRoomData {

  /**
   *
   * @return Id of the Room
   */
  long getRoomId();

  /**
   *
   * @return name of the room
   */
  String getName();

  /**
   *
   * @return capacity of the room
   */
  int getCapacity();
}
