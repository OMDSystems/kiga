package businessLogic.customerComponent;

import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import play.db.jpa.JPABase;

/**
 * Usecase for create, read, update, delete
 * @author Marvin
 */
class CustomerDAO {

  private static CustomerDAO instance = null;

  private CustomerDAO() {
  }

  /**
   * Creates the CustomerDAO
   * @return
   */
  static CustomerDAO createCRUDUseCase() {
    if (instance == null) {
      instance = new CustomerDAO();
    }
    return instance;
  }

  IChildData getChildData(long id) throws ChildNotFoundException {
    IChildData child = (IChildData) ChildEntity.findById(id);
    if (child == null) {
      throw new ChildNotFoundException("No Child with id " + id + " found");
    } else {
      return child;
    }
  }

  long createChild(String name, String familyName, Date dateOfBirth, String allergies, AdressType adress) {
    ChildEntity childEntity = new ChildEntity(name, familyName, dateOfBirth, allergies, adress);
    childEntity.save();
    return childEntity.getId();
  }

  boolean deleteChild(long id) {
    if (!existsChild(id)) {
      return false;
    } else {
      ((ChildEntity) ChildEntity.findById(id)).delete();
      return !existsChild(id);
    }
  }

  private boolean existsChild(long id) {
    return ChildEntity.findById(id) != null;
  }

  public boolean updateChild(long id, String name, String familyName, Date dateofBirth, String allergies, AdressType adress) throws TechnicalProblemException, ChildNotFoundException {
    ChildEntity childEntity = (ChildEntity) this.getChildData(id);
    childEntity.setName(name);
    childEntity.setFamilyName(familyName);
    childEntity.setDateOfBirth(dateofBirth);
    childEntity.setAllergies(allergies);
    childEntity.setAdress(adress);
    childEntity.save();
    return true;
  }

  void deleteAllChildren() {
    for (JPABase jPABase : ChildEntity.findAll()) {
      ((ChildEntity) jPABase)._delete();
    }
  }

  Collection<IChildData> getAllChildren() {
    List<IChildData> result = new ArrayList<IChildData>();
    for (JPABase jPABase : ChildEntity.findAll()) {
      result.add((IChildData) jPABase);
    }
    return result;
  }

  Collection<IChildData> getAllChildrenForGroup(long groupId) {
    Collection<IChildData> result = new LinkedList<IChildData>();
    for (IChildData child : getAllChildren()) {
      if (child.getGroups().contains(groupId)) {
        result.add(child);
      }
    }
    return result;
  }
}
