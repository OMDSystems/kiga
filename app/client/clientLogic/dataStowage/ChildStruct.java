package client.clientLogic.dataStowage;

import businessLogic.customerComponent.IChildData;
import businessLogic.zeroType.AdressType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Marvin
 */
public class ChildStruct implements IChildData{
  
  private final String name;
  private final String familyName;
  private final Date dateOfBirth;
  private final String allergies;
  private final AdressType adress;
  private Collection<Long> groups;
  private Long id;

  private ChildStruct(String name, String familyName, Date dateOfBirth, String allergies, AdressType adress, Collection<Long> groups, Long id) {
    this.name = name;
    this.familyName = familyName;
    this.dateOfBirth = dateOfBirth;
    this.allergies = allergies;
    this.adress = adress;
    this.groups = groups;
    this.id = id;
  }
  
  /**
   * Creates ChildStruct with empty Grouplist and id -1
   * @param name
   * @param familyName
   * @param dateOfBirth
   * @param allergies
   * @param adress
   * @return 
   */
  public static IChildData newChildStruct(String name, String familyName, Date dateOfBirth, String allergies, AdressType adress){
    return new ChildStruct(name, familyName, dateOfBirth, allergies, adress, new ArrayList<Long>(), -1l);
  }
  
  public static IChildData newChildStruct(String name, String familyName, Date dateOfBirth, String allergies, AdressType adress, Collection<Long> groups, Long id){
    return new ChildStruct(name, familyName, dateOfBirth, allergies, adress, groups, id);
  }

//  public static IChildData newChildStruct(IChildData child){
//    return ChildStruct.newChildStruct(child.getName(), child.getFamilyName(), child.getDateOfBirth(), child.getAllergies(), child.getAdress(), child.getGroups(), child.getChildId());
//  }

  public AdressType getAdress() {
    return adress;
  }

  public String getAllergies() {
    return allergies;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getFamilyName() {
    return familyName;
  }

  public String getName() {
    return name;
  }

  public Collection<Long> getGroups() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public long getChildId() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  
  
  

  
}
