package businessLogic.customerComponent;

import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Usecase has methods for advanced information and statistics
 * @author Marvin
 */
class InfoAndStatisticsUseCase {

    /** Singleton instance of this component */
    private static InfoAndStatisticsUseCase instance = null;
    
    /** Neighbour component groupmanagement */
    private IGroupmanagement groupmanagement = null;
    
    /** CRUDUseCase of this component */
    private CRUDUseCase crudUseCase = null;
    
    private InfoAndStatisticsUseCase(){}
    
    private InfoAndStatisticsUseCase(IGroupmanagement groupmanagement, CRUDUseCase crudUseCase){
        this.groupmanagement = groupmanagement;
        this.crudUseCase = crudUseCase;
    }

    /**
     * Creates the Usecase
     * @param groupmanagement Neighbour component groupmanagement
     */
    public static InfoAndStatisticsUseCase createUseCase(IGroupmanagement groupmanagement, CRUDUseCase crudUseCase){
        if (instance == null) {
            instance = new InfoAndStatisticsUseCase(groupmanagement, crudUseCase);
        }
        return instance;
    }

    Collection<IChildData> getAllChildrenForGroup(long groupId) {
        Collection<IChildData> result = new LinkedList<IChildData>();
        for (IChildData child : crudUseCase.getAllChildren()) {
            if (child.getGroups().contains(groupId)) {
                result.add(child);
            }
        }
        return result;
    }

    boolean assignChildToGroup(long childId, long groupId) throws GroupNotFoundException, ChildNotFoundException, TechnicalProblemException {
        ChildEntity child = ChildEntity.findById(childId);
        if (!child.getGroups().contains(groupId)) {
            child.getGroups().add(groupId);
            child.save();
        } else {
            return false;
        }
        return true;
    }
 
//    private boolean existsGroup(long groupId) throws TechnicalProblemException{
//        try {
//            return groupmanagement.getGroupById(groupId) != null;
//        } catch (GroupNotFoundException ex) {
//            return false;
//        }
//    }
    
    private boolean existsChild(long childId) {
        try {
            return crudUseCase.getChildData(childId) != null;
        } catch (ChildNotFoundException ex) {
            return false;
        }
    }
    
}
