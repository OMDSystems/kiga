
package businessLogic.groupComponent;

import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.WeekdayType;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * Entity of Group
 * @author Oliver
 */
@Entity
public class GroupEntity extends Model implements IGroupData{

    private GroupType grouptype;
    private WeekdayType  weekdaytype;
    private double price;
    private String name;
    private long roomId;

    /**
     * create a group
     * @param grouptype
     * @param weekdaytype
     * @param price
     * @param name
     * @param roomId
     */
    public GroupEntity(GroupType grouptype, WeekdayType weekdaytype, double price, String name, long roomId ){
        this.grouptype = grouptype;
        this.weekdaytype = weekdaytype;
        this.price = price;
        this.name = name;
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupEntity other = (GroupEntity) obj;
        if (this.grouptype != other.grouptype) {
            return false;
        }
        if (this.weekdaytype != other.weekdaytype) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.roomId != other.roomId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.grouptype != null ? this.grouptype.hashCode() : 0);
        hash = 23 * hash + (this.weekdaytype != null ? this.weekdaytype.hashCode() : 0);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 23 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 23 * hash + (int) (this.roomId ^ (this.roomId >>> 32));
        return hash;
    }



    public void setGrouptype(GroupType grouptype) {
        this.grouptype = grouptype;
    }

    public void setWeekdaytype(WeekdayType weekdaytype) {
        this.weekdaytype = weekdaytype;
    }

    public GroupType getGroupType() {
        return grouptype;
    }

    public WeekdayType getWeekdayType() {
        return weekdaytype;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }

    public long getGroupId() {
       return this.getId();
    }

    public long getRoomId(){
        return roomId;
    }

    public void setRoomId(long roomId){
        this.roomId = roomId;
    }


}
