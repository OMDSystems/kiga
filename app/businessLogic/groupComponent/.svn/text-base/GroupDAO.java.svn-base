
package businessLogic.groupComponent;

import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.WeekdayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oliver
 */
public class CRUDUseCase {

    private static CRUDUseCase usecase = null;

    private CRUDUseCase(){

    }

    /**
     * Creates the CRUDUseCase
     * @return
     */
    public static CRUDUseCase createCRUDUseCase(){
        if (usecase == null) {
            usecase = new CRUDUseCase();
        }
        return usecase;
    }

    long createEmptyGroup(GroupType groupType, WeekdayType weekdayType, double grouptype, String weekdaytype, long room) {
        //Todo: OW get room by Id und add to room
        GroupEntity group = new GroupEntity(groupType, weekdayType, grouptype, weekdaytype);
        group.save();
        return group.getId();
    }

    boolean deleteGroup(long id) {
      return updateGroup(id,0, "");
    }

    IGroupData getGroupById(long id) {
        return (IGroupData)GroupEntity.findById(id);
    }

//    void deleteAllGroups() {
//        GroupEntity.deleteAll();
//    }

    Map<WeekdayType, Map<GroupType,IGroupData>> getAllGroups() {
        Map<WeekdayType, Map<GroupType,IGroupData>> result = new HashMap<WeekdayType, Map<GroupType,IGroupData>>();
        for (WeekdayType weekday : WeekdayType.values()) {
            Map<GroupType, IGroupData> dayColumn = new HashMap<GroupType, IGroupData>();
            for (GroupType grouptype : GroupType.values()) {
                dayColumn.put(grouptype, null);
            }
            result.put(weekday, dayColumn);
        }
        
        List<GroupEntity> groups = GroupEntity.findAll();

        for (GroupEntity groupEntity : groups) {
            result.get(groupEntity.getWeekdayType()).put(groupEntity.getGroupType(),groupEntity);
        }

        return result;
    }

    boolean updateGroup(long id,double price, String name) {
           if (GroupEntity.findById(id) !=null) {
            GroupEntity group = (GroupEntity)GroupEntity.findById(id);
            group.setPrice(price);
            group.setName(name);
            group.save();
            return true;
        } else {
            return false;
        }
    }

     long createGroup(GroupType groupType, WeekdayType weekdayType, double price, String name, long room) {
//         GroupEntity.find("SELECT OBJECT(x)", groupType, weekdayType);
        List<GroupEntity> groups = GroupEntity.findAll();
         for (GroupEntity groupEntity : groups) {
             if(groupEntity.getWeekdayType() == weekdayType
                     && groupEntity.getGroupType() == groupType){
                 if(updateGroup(groupEntity.getId(),price, name)){
                    return groupEntity.getId();
                 }else{
                     return -1;
                 }
             }
         }
        return -1;
     }

    private boolean existsGroup(long id){
        return GroupEntity.findById(id) != null;
    }

    void clearAll() {
       List<GroupEntity> groups = GroupEntity.findAll();
         for (GroupEntity groupEntity : groups) {
                 deleteGroup(groupEntity.getId());
             }
         }

}
