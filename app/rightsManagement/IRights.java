package rightsManagement;

/**
 * All methods for accessing rightsmanagement
 * @author Marvin
 */
public interface IRights {

    /**
     * Authenticates a user in the systems
     * @param username Username of user
     * @param passwird Password of user
     * @return Token that parmitts access
     * @throws InvalidUsernameException when username is not in system
     * @throws InvalidPasswordException when password does not match with username
     */
    String authenticate(String username, String password) throws InvalidPasswordException, InvalidUsernameException;

    /**
     * Checks whether a certain user is allowed to access a function of the application
     * @param token Token of user granted by <c>authenticate</c>
     * @param functionToAccess Function the user wants to access
     * @return <c>true</c> - if user is allowed to access function<br>
     * <c>false</c> - else
     * @throws InvalidTokenException when token is invalid (i.e. run out)
     * @see #authenticate(java.lang.String, java.lang.String)
     */
    boolean isAccessPermitted(String token, FunctionType functionToAccess) throws InvalidTokenException;
}
