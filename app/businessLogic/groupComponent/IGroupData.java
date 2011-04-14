package businessLogic.groupComponent;

import businessLogic.zeroType.GroupType;
import businessLogic.zeroType.WeekdayType;

/**
 * Get data of Group
 * @author Oliver
 */
public interface IGroupData {

    /**
     *
     * @return GrouptType
     */
    public GroupType getGroupType();

    /**
     *
     * @return WeekdayType
     */
    public WeekdayType getWeekdayType();

    /**
     *
     * @return Price
     */
    public double getPrice();

    /**
     *
     * @return name of the group
     */
    public String getName();

        /**
     *
     * @return id of the Group
     */
    public long getGroupId();
}
