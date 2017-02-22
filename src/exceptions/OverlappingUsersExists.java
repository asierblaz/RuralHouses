package exceptions;
public class OverlappingUsersExists extends Exception {
 private static final long serialVersionUID = 1L;
 
 public OverlappingUsersExists()
  {
    super();
  }
  /**This exception is triggered if there exists an overlapping offer
  *@param String
  *@return None
  */
  public OverlappingUsersExists(String s)
  {
    super(s);
  }
}