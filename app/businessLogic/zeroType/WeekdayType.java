package businessLogic.zeroType;

/**
 * type to identifier Weekdays
 * @author Oliver
 */
public enum WeekdayType {

  MONDAY("monday"),
  TUESDAY("tuesday"),
  WEDNESDAY("wednesday"),
  THURSDAY("thursday"),
  FRIDAY("friday");

  protected final String value;

  @Override
  public String toString() {
    return value;
  }

  WeekdayType(String value) {
    this.value = value;
  }
}
