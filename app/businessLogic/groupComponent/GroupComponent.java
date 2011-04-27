package businessLogic.groupComponent;

import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.TechnicalProblemException;
import businessLogic.zeroType.WeekdayType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OhWeh
 */
public class GroupComponent implements IGroupmanagement{

    private static GroupComponent groupComponent = null;

    private CRUDUseCase crudusecase = null;
   

    private GroupComponent(){
        this.crudusecase = CRUDUseCase.createCRUDUseCase();
    };

    public static GroupComponent createComponent(){
        if(groupComponent == null){
           groupComponent = new GroupComponent();
        }
        return groupComponent;
    }

    public long createGroup(GroupType grouptype, WeekdayType weekdaytype, double price, String name, long room) throws TechnicalProblemException {
        return crudusecase.createGroup(grouptype, weekdaytype, price, name, room);
    }

    public boolean deleteGroup(long id) throws TechnicalProblemException {
        return crudusecase.deleteGroup(id);
    }

    public IGroupData getGroupById(long id) throws TechnicalProblemException {
        return crudusecase.getGroupById(id);
    }

    public Map<WeekdayType, Map<GroupType,Map<IRoomData, List<IGroupData>>>> getAllGroups() throws TechnicalProblemException {
        return crudusecase.getAllGroups();
    }

    public void deleteAllGroups() {
        crudusecase.deleteAllGroups();
    }

//    private void generateEmptyGroups() {
//          for (WeekdayType weekday : WeekdayType.values()) {
//            for (GroupType grouptype : GroupType.values()) {
//                crudusecase.createEmptyGroup(grouptype, weekday, 0.0,"" ,-1);
//            }
//        }
//    }

    public boolean updateGroup(GroupType grouptype, WeekdayType weekdaytype,long id, double price, String name, long roomId) throws TechnicalProblemException {
        return crudusecase.updateGroup(grouptype, weekdaytype, id, price, name, roomId);
    }

//    public void clearAll() {
//        crudusecase.clearAll();
//    }

}
