package businessLogic.groupComponent;

import businessLogic.zeroType.GroupNotFoundException;
import javax.persistence.Entity;
import play.db.jpa.Model;
/**
 *
 * @author Oliver
 */
@Entity
public class WaitingQueueEntity extends Model implements IWaitingQueueData {

    private GroupEntity group;

    WaitingQueueEntity(GroupEntity group){
        this.group = group;
    }

    public long getGroupId() throws GroupNotFoundException {
        return group.getGroupId();
    }

    public long getWaitingQueueId() {
        return this.getId();
    }

}
