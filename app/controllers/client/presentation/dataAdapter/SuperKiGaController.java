package controllers.client.presentation.dataAdapter;

import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.groupComponent.IGroupmanagement;
import play.mvc.*;

/**
 * Controller that has the components of the systems
 * All actual controllers inherit from SuperKiGaController
 * @author Admin
 */
public class SuperKiGaController extends Controller {

    protected static ICustomermanagement customermanagement;

    protected static IGroupmanagement groupmanagement;

    static {
        customermanagement = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent();
        groupmanagement = buildAndConfigure.BuildAndConfigureSystem.getGroupComponent();
    }

    /** Returns the customermanagement of the system */
    protected static ICustomermanagement getCustomermanagement() {
        return customermanagement;
    }

    /** Returns the groupmanagement of the systems */
    protected static IGroupmanagement getGroupmanagement() {
        return groupmanagement;
    }

    @Before
    static void authorize() {
      // TODO: implement authorization stuff
    }

}
