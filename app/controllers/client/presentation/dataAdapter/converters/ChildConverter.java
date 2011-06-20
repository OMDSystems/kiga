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
  
  private static final String DATE_FORMAT_GERMAN = "dd/MM/yyyy";

  private ChildConverter() {
  }

  public static ChildConverter getInstance() {
    return ChildConverterHolder.INSTANCE;
  }

  private Object getChildStowage() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private static class ChildConverterHolder {

    private static final ChildConverter INSTANCE = new ChildConverter();
  }

  public IChildData convertPlayChildDataToIChildData(Scope.Params playChildData) throws DataValidationException {
    final AdressType address = new AdressType(
            playChildData.get("street"),
            playChildData.get("zip"),
            playChildData.get("city"),
            playChildData.get("additional"),
            playChildData.get("street_number"));

    DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_GERMAN);
    Date birthday;
    try {
      birthday = formatter.parse(playChildData.get("date_of_birth"));
    } catch (ParseException ex) {
      System.err.println("falsch geparst");
      throw new DataValidationException(ex.getLocalizedMessage());
    }
    IChildData child = ChildStruct.newChildStruct(
            playChildData.get("first_name"),
            playChildData.get("last_name"),
            birthday,
            playChildData.get("allergies"),
            address);
    return child;
  }
}
