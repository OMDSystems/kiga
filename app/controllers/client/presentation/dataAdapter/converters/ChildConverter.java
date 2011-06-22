package controllers.client.presentation.dataAdapter.converters;

import businessLogic.customerComponent.IChildData;
import businessLogic.zeroType.AdressType;
import client.clientLogic.dataStowage.ChildStruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import play.mvc.Scope;

/**
 *
 * @author Marvin
 */
public class ChildConverter {

  private static final String DATE_FORMAT_GERMAN = "dd.MM.yyyy";
  private static final String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";
  private static final String GERMAN_DATE_REGEX = "[0-3][0-9]\\.[0-1][0-9]\\.[0-9]{4}";

  private ChildConverter() {
  }

  public static ChildConverter getInstance() {
    return ChildConverterHolder.INSTANCE;
  }

  private static class ChildConverterHolder {

    private static final ChildConverter INSTANCE = new ChildConverter();
  }

  public IChildData convertPlayChildDataToIChildData(Scope.Params playChildData) throws DataValidationException {
    validateAddressAttributes(playChildData.get("street"),
            playChildData.get("zip"),
            playChildData.get("city"),
            playChildData.get("additional"),
            playChildData.get("street_number"));

    final AdressType address = new AdressType(
            playChildData.get("street"),
            playChildData.get("zip"),
            playChildData.get("city"),
            playChildData.get("additional"),
            playChildData.get("street_number"));

    Date birthday = makeValidBirthay(playChildData.get("date_of_birth"));

    IChildData child = ChildStruct.newChildStruct(
            playChildData.get("first_name"),
            playChildData.get("last_name"),
            birthday,
            playChildData.get("allergies"),
            address);

    validateStringAttributes(child);
    return child;
  }

  private Date makeValidBirthay(String param) throws DataValidationException {
    matchesRegex(param, GERMAN_DATE_REGEX);
    DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_GERMAN);
    Date birthday;
    try {
      birthday = formatter.parse(param);
    } catch (ParseException ex) {
      System.err.println("failed to parse birthday date string");
      throw new DataValidationException(ex.getLocalizedMessage());
    }
    return birthday;
  }

  private void validateStringAttributes(IChildData childData) throws DataValidationException {
    isPresent(childData.getName());
    isPresent(childData.getFamilyName());
  }

  private void validateAddressAttributes(String street, String zip, String city, String additional, String streetnumber) throws DataValidationException {
    isPresent(street);
    isPresent(city);
    isPresent(zip);
    isPresent(streetnumber);
    matchesRegex(streetnumber, "[1-9][0-9]*[a-z]?");
    matchesRegex(zip, "[1-9][0-9]{4}");
  }

  private void isPresent(String string) throws DataValidationException {
    if (string == null) {
      throw new DataValidationException();
    } else {
      if (string.length() == 0) {
        throw new DataValidationException();
      }
    }
  }

  private void matchesRegex(String input, String regex) throws DataValidationException {
    if (!input.matches(regex)) {
      System.err.println(input+" did not match "+regex);
      throw new DataValidationException();
    }
  }
}
