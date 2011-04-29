package businessLogic.groupComponent;

import businessLogic.zeroType.GroupNotFoundException;

/**
 *
 * @author Oliver
 */
public interface IWaitingQueueData {

    /**
     *
     * @return Id of the group
     */
    public long getGroupId() throws GroupNotFoundException;


    public long getWaitingQueueId();

}
