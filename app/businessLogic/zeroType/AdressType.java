package businessLogic.zeroType;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * AdressType of children and parents
 * @author OhWeh
 */
@Entity
public class AdressType extends Model {

  private String street;
  private String zip;
  protected String town;
  private String additional;
  private String houseNumber;

  /**
   * Creates a new AdressType
   * @param street
   * @param zip
   * @param town
   * @param additional
   * @param houseNumber
   */
  public AdressType(String street, String zip, String town, String additional, String houseNumber) {
    this.street = street;
    this.zip = zip;
    this.town = town;
    this.additional = additional;
    this.houseNumber = houseNumber;
  }

  /**
   * Get the value of street
   *
   * @return the value of street
   */
  public String getStreet() {
    return street;
  }

  /**
   * Set the value of street
   *
   * @param street new value of street
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Get the value of zip
   *
   * @return the value of zip
   */
  public String getZip() {
    return zip;
  }

  /**
   * Set the value of zip
   *
   * @param zip new value of zip
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * Get the value of town
   *
   * @return the value of town
   */
  public String getTown() {
    return town;
  }

  /**
   * Set the value of town
   *
   * @param town new value of town
   */
  public void setTown(String town) {
    this.town = town;
  }

  /**
   * Get the value of additional
   *
   * @return the value of additional
   */
  public String getAdditional() {
    return additional;
  }

  /**
   * Set the value of additional
   *
   * @param additional new value of additional
   */
  public void setAdditional(String additional) {
    this.additional = additional;
  }

  /**
   * Get the value of houseNumber
   *
   * @return the value of houseNumber
   */
  public String getHouseNumber() {
    return houseNumber;
  }

  /**
   * Set the value of houseNumber
   *
   * @param houseNumber new value of houseNumber
   */
  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final AdressType other = (AdressType) obj;
    if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
      return false;
    }
    if ((this.zip == null) ? (other.zip != null) : !this.zip.equals(other.zip)) {
      return false;
    }
    if ((this.town == null) ? (other.town != null) : !this.town.equals(other.town)) {
      return false;
    }
    if ((this.additional == null) ? (other.additional != null) : !this.additional.equals(other.additional)) {
      return false;
    }
    if ((this.houseNumber == null) ? (other.houseNumber != null) : !this.houseNumber.equals(other.houseNumber)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 83 * hash + (this.street != null ? this.street.hashCode() : 0);
    hash = 83 * hash + (this.zip != null ? this.zip.hashCode() : 0);
    hash = 83 * hash + (this.town != null ? this.town.hashCode() : 0);
    hash = 83 * hash + (this.additional != null ? this.additional.hashCode() : 0);
    hash = 83 * hash + (this.houseNumber != null ? this.houseNumber.hashCode() : 0);
    return hash;
  }
}
