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
        if (existsGroup(groupId)) {
            if (existsChild(childId)) {
                ChildEntity child = ChildEntity.findById(childId);
                if (!child.getGroups().contains(groupId)) {
                    child.getGroups().add(groupId);
                    child.save();
                }else{
                    return false;
                }
            }else{
               throw new ChildNotFoundException("Child with id "+ childId +"not found."); 
            }
        } else {
            throw new GroupNotFoundException("Group with id "+ groupId + "not found.");
        }
        return true;
    }
    
    private boolean existsGroup(long groupId) throws TechnicalProblemException{
        return groupmanagement.getGroupById(groupId) != null;
    }
    
    private boolean existsChild(long childId) {
        try {
            return crudUseCase.getChildData(childId) != null;
        } catch (ChildNotFoundException ex) {
            return false;
        }
    }
    
}
