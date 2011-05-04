package rightsManagement;

/**
 * All Methods for administrating rights
 * @author Marvin
 */
public interface IRightsAdministration {

  void addUser(String username, String password, UsergroupType group) throws InvalidUsernameException, InvalidPasswordException;
}
