package businessLogic.customerComponent;

import businessLogic.groupComponent.GroupEntity;
import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * This Usecase has methods for advanced information and statistics
 * @author Marvin
 */
class InfoAndStatisticsUseCase {

  /** Singleton instance of this component */
  private static InfoAndStatisticsUseCase instance = null;
  /** Neighbour component groupmanagement */
  private IGroupmanagement groupmanagement = null;
  /** CustomerDAO of this component */
  private CustomerDAO crudUseCase = null;

  private InfoAndStatisticsUseCase() {
  }

  private InfoAndStatisticsUseCase(IGroupmanagement groupmanagement, CustomerDAO crudUseCase) {
    this.groupmanagement = groupmanagement;
    this.crudUseCase = crudUseCase;
  }

  /**
   * Creates the Usecase
   * @param groupmanagement Neighbour component groupmanagement
   */
  public static InfoAndStatisticsUseCase createUseCase(IGroupmanagement groupmanagement, CustomerDAO crudUseCase) {
    if (instance == null) {
      instance = new InfoAndStatisticsUseCase(groupmanagement, crudUseCase);
    }
    return instance;
  }

//  Collection<IChildData> getAllChildrenForGroup(long groupId) throws GroupNotFoundException {
//    groupmanagement.getGroupById(groupId);
//    Collection<IChildData> result = new LinkedList<IChildData>();
//    for (IChildData child : crudUseCase.getAllChildren()) {
//      if (child.getGroups().contains(groupId)) {
//        result.add(child);
//      }
//    }
//    return result;
//  }
  boolean assignChildToGroup(long childId, long groupId) throws GroupNotFoundException, ChildNotFoundException, TechnicalProblemException {
    ChildEntity child = (ChildEntity) crudUseCase.getChildData(childId);
    GroupEntity group = (GroupEntity) groupmanagement.getGroupById(groupId);
    if (child.getGroups().contains(group.getId())) {
      return false;
    } else {
      boolean addGroup = child.addGroup(group);
      group.save();
      return addGroup;
    }
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
