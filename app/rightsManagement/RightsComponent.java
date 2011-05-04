package rightsManagement;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.WS.WSRequest;

/**
 * This component realizes rights management as claimed by Hamburg
 * @author Marvin
 */
public class RightsComponent implements IRights, IRightsAdministration {

  private static final String REMOTE_RIGHTSMANAGEMENT_URL = "localhost:9001";
  /** Singleton instance of this component */
  private static RightsComponent instance = null;

  private RightsComponent() {
  }

  /**
   * Creates the rights component
   * @return singleton instance of component
   */
  public static RightsComponent createComponent() {
    if (instance == null) {
      instance = new RightsComponent();
    }
    return instance;
  }

  public String authenticate(String username, String password) throws InvalidPasswordException, InvalidUsernameException {
    WS.url(REMOTE_RIGHTSMANAGEMENT_URL + "/administration/auth_user?username=" + username + "?password=" + password);
    throw new UnsupportedOperationException("Delegate to other play-roject via REST interface");
  }

  public boolean isAccessPermitted(String token, FunctionType functionToAccess) throws InvalidTokenException {
    WS.url(REMOTE_RIGHTSMANAGEMENT_URL + "/administration/auth_user ");
    throw new UnsupportedOperationException("Delegate to other play-roject via REST interface");
  }

  public void addUser(String username, String password, UsergroupType group) throws InvalidUsernameException, InvalidPasswordException {
    WS.url(REMOTE_RIGHTSMANAGEMENT_URL + "?username=" + username + "?password=" + password + "?usergroup=" + group.toString());
    throw new UnsupportedOperationException("Delegate to other play-roject via REST interface");
  }
}
