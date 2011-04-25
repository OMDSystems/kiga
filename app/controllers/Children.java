package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

//import models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import fassade.SuperKiGaController;
import businessLogic.customerComponent.IChildData;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.AdressType;

public class Children extends SuperKiGaController {

  public static void index() {
    renderJSON(getCustomermanagement().getAllChildren());
  }

  public static void show(long id) {
    try {
      IChildData child = getCustomermanagement().getChildData(id);
      renderJSON(child);
    } catch(TechnicalProblemException e) {
      notFound();
    }
  }

  public static void create() {
    try {
      final AdressType address = new AdressType(
        params.get("street"),
        params.get("zip"),
        params.get("town"),
        params.get("additional"),
        params.get("houseNumber")
      );

      Date birthday = DateFormat.getInstance().parse(params.get("date_of_birth"));

      getCustomermanagement().createChild(
        params.get("name"),
        params.get("last_name"),
        birthday,
        params.get("allergies"),
        address
      );
    } catch(ParseException e) {
      error();
    } catch(TechnicalProblemException e) {
      error();
    }
  }
}