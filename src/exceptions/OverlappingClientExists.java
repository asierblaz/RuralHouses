package exceptions;
public class OverlappingClientExists extends Exception {
 private static final long serialVersionUID = 1L;
 
 public OverlappingClientExists()
  {
    super();
  }
  /**This exception is triggered if there exists an overlapping offer
  *@param String
  *@return None
  */
  public OverlappingClientExists(String s)
  {
    super(s);
  }
}