package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

//import models.*;

import fassade.SuperKiGaController;

public class Application extends SuperKiGaController {

  public static void index() {
    render();
  }

  public static void check() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("online", true);
    renderJSON(result);
  }
}
