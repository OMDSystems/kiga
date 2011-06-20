package controllers;
import controllers.client.presentation.dataAdapter.SuperKiGaController;


import java.util.*;

//import models.*;


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
