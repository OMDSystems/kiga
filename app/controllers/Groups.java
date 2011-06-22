package controllers;

import businessLogic.groupComponent.IRoomData;
import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.RoomNotFoundException;
import businessLogic.zeroType.WeekdayType;
import java.util.logging.Level;
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
import businessLogic.groupComponent.IGroupData;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.AdressType;

public class Groups extends SuperKiGaController {

  public static void index() throws RoomNotFoundException {
    try {
      /*long roomId = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent().createRoom("Darkroom", 10);
      buildAndConfigure.BuildAndConfigureSystem.getGroupComponent()
              .createGroup(GroupType.EARLY, WeekdayType.MONDAY, 23, "Warme Brüder", roomId);*/
      Map<WeekdayType, Map<GroupType, Map<IRoomData, List<IGroupData>>>> groups
              = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent()
                .getAllGroups();
      List<GroupType> types = Arrays.asList(GroupType.values());
      List<WeekdayType> days = Arrays.asList(WeekdayType.values());
      render(groups, types, days);
    } catch (TechnicalProblemException ex) {
      java.util.logging.Logger.getLogger(Groups.class.getName()).log(Level.SEVERE, null, ex);
      error();
    }
  }

  public static void show(long id) {
    Collection<IChildData> children = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent().getAllChildren();
    renderJSON(children);
  }

  public static void createRandom() throws Exception {
    long roomId = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent().createRoom("Darkroom", 10);
    long groupId = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent().createGroup(GroupType.LATE, WeekdayType.WEDNESDAY, 23, "Gonzo Gang", roomId);
    //long childId = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent().createChild("Tom", "Kauschat", new Date(), "none", new AdressType("Sesams", "012345", "Hamburg", "", "17"));
  }

  public static void create() {
  }
}

