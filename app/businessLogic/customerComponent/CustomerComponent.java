package businessLogic.customerComponent;

import businessLogic.groupComponent.IGroupmanagement;
import businessLogic.mocks.financialComponent.IInvoiceData;
import businessLogic.mocks.financialComponent.MockUseCase;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.Collection;
import java.util.Date;

/**
 * Represents the customer component
 * @author Admin
 */
public class CustomerComponent implements ICustomermanagement {

  /** CustomerDAO of this component */
  private CustomerDAO customerDAO = null;
  /** Info and statistics usecase of this component */
  private InfoAndStatisticsUseCase infoAndStatisticsUseCase = null;
  /** Singleton instance of this component */
  private static CustomerComponent instance = null;

  /** Should not be used */
  private CustomerComponent() {
  }

  /**
   * Creates the customer component
   * @param groupmanagement
   */
  private CustomerComponent(IGroupmanagement groupmanagement) {
    this.customerDAO = CustomerDAO.createCRUDUseCase();
    this.infoAndStatisticsUseCase = InfoAndStatisticsUseCase.createUseCase(groupmanagement, customerDAO);
  }

  /**
   * Creates the customer component
   * @return component
   */
  public static CustomerComponent createComponent(IGroupmanagement groupmanagement) {
    if (instance == null) {
      instance = new CustomerComponent(groupmanagement);
    }
    return instance;
  }

  public boolean isValidChildData(String name, String familyName, Date dateofBirth, String allergies, AdressType adress) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public long createChild(String name, String familyName, Date dateofBirth, String allergies, AdressType adress) throws TechnicalProblemException {
    return customerDAO.createChild(name, familyName, dateofBirth, allergies, adress);
  }

  public boolean deleteChild(long id) throws TechnicalProblemException {
    return customerDAO.deleteChild(id);
  }

  public IChildData getChildData(long id) throws ChildNotFoundException {
    return customerDAO.getChildData(id);
  }

  public boolean updateChild(long id, String name, String familyName, Date dateofBirth, String allergies, AdressType adress) throws TechnicalProblemException, ChildNotFoundException {
    return customerDAO.updateChild(id, name, familyName, dateofBirth, allergies, adress);
  }

  public void deleteAllChildren() {
    customerDAO.deleteAllChildren();
  }

  public Collection<IChildData> getAllChildren() {
    return customerDAO.getAllChildren();
  }

  public boolean assignChildToGroup(long childId, long groupId) throws TechnicalProblemException, ChildNotFoundException, GroupNotFoundException {
    return infoAndStatisticsUseCase.assignChildToGroup(childId, groupId);
  }

  public Collection<IChildData> getAllChildrenForGroup(long groupId) {
    return customerDAO.getAllChildrenForGroup(groupId);
  }

  public Collection<IInvoiceData> getInvoicesOfCurrentMonth() throws TechnicalProblemException {
    return MockUseCase.getInvoicesOfCurrentMonth();
  }
}
