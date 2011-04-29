package businessLogic.groupComponent;

import businessLogic.customerComponent.ChildEntity;
import businessLogic.zeroType.GroupNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import play.db.jpa.Model;
/**
 *
 * @author Oliver
 */
@Entity
public class WaitingQueueEntity extends Model implements IWaitingQueueData {

//    private List<ChildEntity> childs = new ArrayList<ChildEntity>();

    WaitingQueueEntity(){
    }


    public List<Long> getChildInWaitingQueue() {
        //TODO: OW fertig stellen
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
