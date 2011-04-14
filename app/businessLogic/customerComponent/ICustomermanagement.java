package businessLogic.customerComponent;

import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.GroupNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.Collection;
import java.util.Date;

/**
 * Methods for management of children
 * @author Admin
 */
public interface ICustomermanagement {
    /**
     * Checks whether a given set a data for creating a child is valid
     * @param name First Name
     * @param familyName Family name
     * @param dateofBirth Date of birth
     * @param allergies Known allergies of child
     * @param adress Main adress of child
     * @return <code>true</code> if valid<br>
     * <code>false</code> else
     */
//    boolean isValidChildData(String name, String familyName, Date dateofBirth, String allergies, AdressType adress);
    
    /**
     * Returns the data of a child
     * @param id technical id of child
     * @return Interface with only getters
     * @throws TechnicalProblemException if database not available
     */
    IChildData getChildData(long id) throws TechnicalProblemException;


    /**
     * Creates a new child and saves it to database
     * @param name First Name
     * @param familyName Family name
     * @param dateofBirth Date of birth
     * @param allergies Known allergies of child
     * @param adress Main adress of child
     * @return technical ID of created child
     * @throws TechnicalProblemException if database not available
     */
    long createChild(String name, String familyName, Date dateofBirth, String allergies, AdressType adress) throws TechnicalProblemException;

    /**
     * Deletes a child from database
     * @param id Technical id of child
     * @return <code>true</code>if successfully deleted<br><code>false</code>if id not in database
     * @throws TechnicalProblemException if database not available
     */
    boolean deleteChild(long id) throws TechnicalProblemException;

    /**
     * Updates the attributes of a child
     * @param id technical id of the child
     * @param name new name
     * @param familyName new family name
     * @param dateofBirth new birthday
     * @param allergies new allergies
     * @param adress new adress
     * @return <c>true</c> if successfully updated<br>
     * <c>false</c> else
     * @throws if database not available
     */
    boolean updateChild(long id, String name, String familyName, Date dateofBirth, String allergies, AdressType adress) throws TechnicalProblemException;

    
    /**
     * Deletes all Children
     */
    void deleteAllChildren();
    
    /** 
     * Returns the data of all children in the system
     * @return Collection with data
     */
    Collection<IChildData> getAllChildren();

    /**
     * Returns all children that are currently in a group
     * @param groupId technical id of group
     * @return Collection with children data
     */
    Collection<IChildData> getAllChildrenForGroup(long groupId);
    
    /**
     * Assigns a child to a group
     * @param childId technical id of child
     * @param groupId technical id of group
     * @return <c>true</c>if succesfully assigned<br>
     * <c>false</c> if group is full
     * @throws GroupNotFoundException if groupId not valid
     * @throws ChildNotFoundException if childId not valid
     */
//    boolean assignChildToGroup(long childId, long groupId) throws TechnicalProblemException, GroupNotFoundException, ChildNotFoundException;



}
