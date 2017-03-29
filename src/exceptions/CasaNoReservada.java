package exceptions;
public class CasaNoReservada extends Exception {
 private static final long serialVersionUID = 1L;
 
 public CasaNoReservada()
  {
    super();
  }
  /**This exception is triggered if an offer can not be booked 
  *@param String
  *@return None
  */
  public CasaNoReservada(String s)
  {
    super(s);
  }
}