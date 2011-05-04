package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

//import models.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import fassade.SuperKiGaController;
import businessLogic.customerComponent.IChildData;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;

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
    } catch(ChildNotFoundException ex){
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
        params.get("street_number")
      );

      SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
      Date birthday = formatter.parse(params.get("date_of_birth"));

      getCustomermanagement().createChild(
        params.get("first_name"),
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