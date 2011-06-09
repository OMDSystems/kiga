package rightsManagementProxy;

/**
 * Defining different types of users
 * @author Marvin
 */
public enum UsergroupType {

  MANAGER, PARENT;

  @Override
  public String toString() {
    return super.name().toLowerCase();
  }
}
