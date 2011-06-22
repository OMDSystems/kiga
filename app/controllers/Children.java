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
import businessLogic.groupComponent.IRoomData;
import businessLogic.groupComponent.IGroupData;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.WeekdayType;
import businessLogic.zeroType.ChildNotFoundException;
import client.clientLogic.dataStowage.ChildStowage;
import controllers.client.presentation.dataAdapter.converters.ChildConverter;
import controllers.client.presentation.dataAdapter.converters.DataValidationException;
import java.text.DateFormat;

public class Children extends SuperKiGaController {

  public static void index() throws Exception {
    Collection<IChildData> children = getChildStowage().getAllChildren();
    List<GroupType> types = Arrays.asList(GroupType.values());
    List<WeekdayType> days = Arrays.asList(WeekdayType.values());
    Map<WeekdayType, Map<GroupType, Map<IRoomData, List<IGroupData>>>> groups
              = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent()
                .getAllGroups();
    render(children, types, days, groups);
  }

  private static ChildStowage getChildStowage() {
    return ChildStowage.getInstance();
  }

  private static ChildConverter getChildConverter() {
    return ChildConverter.getInstance();
  }

  public static void show(long id) {
    try {
      IChildData child = getChildStowage().getChildData(id);
      renderJSON(child);
    } catch (TechnicalProblemException e) {
      error();
    } catch (ChildNotFoundException ex) {
      notFound();
    }
  }

  public static void create() {
    try {
      IChildData playChildData = getChildConverter().convertPlayChildDataToIChildData(params);
      getChildStowage().createChild(playChildData);
      ok();
    } catch (DataValidationException ex) {
      badRequest();
    } catch (Exception ex) {
      error();
    }
  }
}
