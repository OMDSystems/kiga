package businessLogic.customerComponent;

import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import play.db.jpa.JPABase;

/**
 * Usecase for create, read, update, delete
 * @author Marvin
 */
class CRUDUseCase {
    
    private static CRUDUseCase instance = null;
    
    private CRUDUseCase(){
        
    }
    
    /**
     * Creates the CRUDUseCase
     * @return 
     */
    static CRUDUseCase createCRUDUseCase(){
        if (instance == null) {
            instance = new CRUDUseCase();
        }
        return instance;
    }
    
    IChildData getChildData(long id) throws ChildNotFoundException{
        IChildData child = (IChildData)ChildEntity.findById(id);
        if (child == null) {
            throw new ChildNotFoundException("No Child with id "+id+" found");
        } else {
            return child;
        }
    }
    
    long createChild(String name, String familyName, Date dateOfBirth, String allergies, AdressType adress){
        ChildEntity childEntity = new ChildEntity(name, familyName, dateOfBirth, allergies, adress);
        childEntity.save();
        return childEntity.getId();
    }

    boolean deleteChild(long id) {
        if (!existsChild(id)) {
            return false;
        }else{
            ((ChildEntity)ChildEntity.findById(id)).delete();
            return !existsChild(id);
        }
    }
    
    private boolean existsChild(long id){
        return ChildEntity.findById(id) != null;
    }
    
    public boolean updateChild(long id, String name, String familyName, Date dateofBirth, String allergies, AdressType adress) throws TechnicalProblemException {
        if (existsChild(id)) {
            ChildEntity childEntity = (ChildEntity)ChildEntity.findById(id);
            childEntity.setName(name);
            childEntity.setFamilyName(familyName);
            childEntity.setDateOfBirth(dateofBirth);
            childEntity.setAllergies(allergies);
            childEntity.setAdress(adress);
            childEntity.save();
            return true;
        } else {
            return false;
        }
    }

    void deleteAllChildren() {
        for (JPABase jPABase : ChildEntity.findAll()) {
            ((ChildEntity)jPABase)._delete();
        }
    }

    Collection<IChildData> getAllChildren() {
        List<IChildData> result = new ArrayList<IChildData>();
        for (JPABase jPABase : ChildEntity.findAll()) {
            result.add((IChildData)jPABase);
        }
        return result;
    }
}
