package businessLogic.groupComponent;

import businessLogic.zeroType.GroupNotFoundException;
import java.util.List;

/**
 *
 * @author Oliver
 */
public interface IWaitingQueueData {


    /**
     *
     * @return List of Children in Queue. Index is position in queue
     */
    public List<Long> getChildInWaitingQueue();

}
