package businessLogic.groupComponent;

import businessLogic.customerComponent.ChildEntity;
import businessLogic.zeroType.GroupNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import play.db.jpa.Model;

/**
 *
 * @author Oliver
 */
@Entity
public class WaitingQueueEntity extends Model implements IWaitingQueueData {

  @ManyToMany
  private List<ChildEntity> children = new ArrayList<ChildEntity>();

  WaitingQueueEntity() {
  }

  public List<Long> getChildInWaitingQueue() {
    List<Long> result = new ArrayList<Long>();
    for (ChildEntity childEntity : children) {
      result.add((Long) childEntity.getId());
    }
    return result;
  }

  boolean addChildToWaitingQueue(long childId) {
    ChildEntity child = ChildEntity.findById(childId);
    return children.add(child);
  }

  boolean removeChildFromWaitingQueue(long childId) {
    ChildEntity child = ChildEntity.findById(childId);
    return children.remove(child);
  }
}
