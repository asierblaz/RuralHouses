package exceptions;
public class OverlappingHouseExists extends Exception {
 private static final long serialVersionUID = 1L;
 
 public OverlappingHouseExists()
  {
    super();
  }
  /**This exception is triggered if there exists an overlapping offer
  *@param String
  *@return None
  */
  public OverlappingHouseExists(String s)
  {
    super(s);
  }
}