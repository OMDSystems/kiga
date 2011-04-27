package buildAndConfigure;


import businessLogic.customerComponent.CustomerComponent;
import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.GroupComponent;
import businessLogic.groupComponent.IGroupmanagement;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import rightsManagement.IRights;
import rightsManagement.IRightsAdministration;
import rightsManagement.RightsComponent;

/**
 * Builds business logic and configures Controllers in fassade via dependency injection
 * @author Marvin
 */
@OnApplicationStart
public class BuildAndConfigureSystem extends Job<String> {

    private static IRights rights = null;

    private static IRightsAdministration rightsAdministration = null;

    private static IGroupmanagement groupComponent = null;

    private static ICustomermanagement customerComponent = null;

    @Override
    public void doJob() {
        buildAndConfigureSystem();
    }

    @Override
    public String doJobWithResult(){
        doJob();
        return "";
    }

    public static void buildAndConfigureSystem(){
        groupComponent = GroupComponent.createComponent();
        customerComponent = CustomerComponent.createComponent(groupComponent);
        RightsComponent rightsComponent = RightsComponent.createComponent();
        rights = rightsComponent;
        rightsAdministration = rightsComponent;
    }

    /**
     * Returns the customer compoennt of the system
     * @return customer component
     */
    public static ICustomermanagement getCustomerComponent() {
        return customerComponent;
    }

    /**
     * Returns the group compoennt of the system
     * @return group component
     */
    public static IGroupmanagement getGroupComponent() {
        return groupComponent;
    }

    /**
     * Returns the rightsManagement of the system
     * @return rights management
     */
    public static IRights getRights() {
        return rights;
    }

    /**
     * Returns the rightsAdministration of the system
     * @return rights administration
     */
    public static IRightsAdministration getRightsAdministration() {
        return rightsAdministration;
    }

    


}
