package mx.com.axity.webapi.soap.api.commons.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data transfer object for account.
 * 
 * @author guillermo.segura@axity.com
 */
public class AccountDTO
{
  private int id;
  private PersonDTO person;
  private BigDecimal balance;
  private List<AccountMovementDTO> movements;
  /**
   * @return the id
   */
  public int getId()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId( int id )
  {
    this.id = id;
  }

  /**
   * @return the person
   */
  public PersonDTO getPerson()
  {
    return person;
  }

  /**
   * @param person the person to set
   */
  public void setPerson( PersonDTO person )
  {
    this.person = person;
  }

  /**
   * @return the balance
   */
  public BigDecimal getBalance()
  {
    return balance;
  }

  /**
   * @param balance the balance to set
   */
  public void setBalance( BigDecimal balance )
  {
    this.balance = balance;
  }

  /**
   * @return the movements
   */
  public List<AccountMovementDTO> getMovements()
  {
    return movements;
  }

  /**
   * @param movements the movements to set
   */
  public void setMovements( List<AccountMovementDTO> movements )
  {
    this.movements = movements;
  }

}
