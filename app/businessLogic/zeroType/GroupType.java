package businessLogic.zeroType;

public enum GroupType {

  EARLY("early"),
  MORNING("morning"),
  AFTERNOON("afternoon"),
  WHOLEDAY("wholeday"),
  LATE("late");

  protected final String value;

  @Override
  public String toString() {
    return value;
  }

  GroupType(String value) {
    this.value = value;
  }
}
