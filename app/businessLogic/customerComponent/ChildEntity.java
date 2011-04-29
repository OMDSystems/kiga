package businessLogic.customerComponent;

import businessLogic.groupComponent.GroupEntity;
import businessLogic.zeroType.AdressType;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import play.db.jpa.Model;

/**
 * A Child is a child that visites the kindergarden
 * @author Marvin
 */
@Entity
class ChildEntity extends Model implements IChildData{

    private String name;
    
    private String familyName;
    
    private Date dateOfBirth;
    
    private String allergies;
    
    private AdressType adress;


//    @MapsId("groupEntity")
//    @JoinColumn(name="group_fk", referencedColumnName="id")
    @ManyToMany(
        targetEntity=GroupEntity.class,
        cascade={CascadeType.ALL}
    )
    @JoinTable(
        name="CHILD_GROUP",
        joinColumns=@JoinColumn(name="CHILD_ID",referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(name="GROUP_ID",referencedColumnName="ID")
    )
    private Collection<Long> groups;

    /**
     * Creates a new child
     * @param name
     * @param familyName
     * @param dateOfBirth
     * @param allergies
     * @param adress 
     */
    public ChildEntity(String name, String familyName, Date dateOfBirth, String allergies, AdressType adress) {
        this.name = name;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.allergies = allergies;
        this.adress = adress;
        this.groups = new LinkedList<Long> ();
    }

    public AdressType getAdress() {
        return adress;
    }

    public void setAdress(AdressType adress) {
        this.adress = adress;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Long> getGroups() {
        return groups;
    }

    public void setGroups(List<Long> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChildEntity other = (ChildEntity) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.familyName == null) ? (other.familyName != null) : !this.familyName.equals(other.familyName)) {
            return false;
        }
        if ((this.allergies == null) ? (other.allergies != null) : !this.allergies.equals(other.allergies)) {
            return false;
        }
        if (this.adress != other.adress && (this.adress == null || !this.adress.equals(other.adress))) {
            return false;
        }
        if (this.dateOfBirth != other.dateOfBirth && (this.dateOfBirth == null || !this.dateOfBirth.equals(other.dateOfBirth))) {
            return false;
        }
        if (this.groups != other.groups && (this.groups == null || !this.groups.equals(other.groups))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.familyName != null ? this.familyName.hashCode() : 0);
        hash = 83 * hash + (this.dateOfBirth != null ? this.dateOfBirth.hashCode() : 0);
        hash = 83 * hash + (this.allergies != null ? this.allergies.hashCode() : 0);
        hash = 83 * hash + (this.adress != null ? this.adress.hashCode() : 0);
        hash = 83 * hash + (this.groups != null ? this.groups.hashCode() : 0);
        return hash;
    }

    @Override
    public long getChildId() {
        return this.getId();
    }

    
    

    
    
    
    
}
