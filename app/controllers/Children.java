package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

//import models.*;

import flexjson.JSONSerializer;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import controllers.client.presentation.dataAdapter.SuperKiGaController;
import businessLogic.customerComponent.IChildData;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import client.clientLogic.dataStowage.ChildStowage;
import controllers.client.presentation.dataAdapter.SuperKiGaController;
import controllers.client.presentation.dataAdapter.SuperKiGaController;
import controllers.client.presentation.dataAdapter.converters.ChildConverter;
import java.text.DateFormat;

public class Children extends SuperKiGaController {
  

  public static void index() {
    Collection<IChildData> children = getCustomermanagement().getAllChildren();
    //renderTemplate("Children/list.json", children);
    render(children);
  }
  
  private static ChildStowage getChildStowage(){
    return ChildStowage.getInstance();
  }
  
  private static ChildConverter getChildConverter(){
    return ChildConverter.getInstance();
  }

  public static void show(long id) {
    try {
      IChildData child = getChildStowage().getChildData(id);
      renderJSON(child);
    } catch(TechnicalProblemException e) {
      error();
    } catch(ChildNotFoundException ex){
      notFound();
    }
  }

  public static void create() {
    try {
      IChildData playChildData = getChildConverter().convertPlayChildDataToIChildData(params);
      getChildStowage().createChild(playChildData);
      ok();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      error();
    }
  }
}
