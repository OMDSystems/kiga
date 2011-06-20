package client.clientLogic.dataStowage;

import businessLogic.customerComponent.IChildData;
import businessLogic.customerComponent.ICustomermanagement;
import businessLogic.zeroType.AdressType;
import businessLogic.zeroType.ChildNotFoundException;
import businessLogic.zeroType.TechnicalProblemException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages stowage of children in client
 * @author Marvin, Olli
 */
public class ChildStowage {
  
  protected static final long DELAY_OF_RECACHE_IN_MILLIS = 5000;
  
  protected Map<Long, IChildData> childCache;

  protected ICustomermanagement customermanagement;

  /** Returns the customermanagement of the system */
  protected ICustomermanagement getCustomermanagement() {
    return customermanagement;
  }

  private ChildStowage() {
    customermanagement = buildAndConfigure.BuildAndConfigureSystem.getCustomerComponent();
    childCache = new HashMap<Long, IChildData>();
  }

  public static ChildStowage getInstance() {
    return ChildStowageHolder.INSTANCE;
  }

  public Collection<IChildData> getAllChildren() {
    return customermanagement.getAllChildren();
  }

  private static class ChildStowageHolder {

    private static final ChildStowage INSTANCE = new ChildStowage();
  }

  
  public long createChild(IChildData childData) throws TechnicalProblemException, ChildNotFoundException {
    long childID = customermanagement.createChild(childData.getName(), childData.getFamilyName(), childData.getDateOfBirth(), childData.getAllergies(), childData.getAdress());
    return cacheChild(childID).getChildId();
  }

  /**
   * {@link ICustomermanagement.getChildData }
   */
  public IChildData getChildData(long id) throws ChildNotFoundException, TechnicalProblemException{
    if (childCache.containsKey(id)) {
      return childCache.get(id);
    } else {
      return cacheChild(id);
    }
  }
  
  protected IChildData cacheChild(final long id) throws TechnicalProblemException, ChildNotFoundException{
    IChildData childData = customermanagement.getChildData(id);
    childCache.put(id, childData);
    
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        try {
          cacheChild(id);
        } catch (TechnicalProblemException ex) {
          System.err.println(ex.getLocalizedMessage());
        } catch (ChildNotFoundException ex) {
          System.err.println(ex.getLocalizedMessage());
        }
      }
    };
    
    Timer timer = new Timer();
    timer.schedule(task, (new Date()).getTime()+DELAY_OF_RECACHE_IN_MILLIS);
    
    return childData;
  }
}
