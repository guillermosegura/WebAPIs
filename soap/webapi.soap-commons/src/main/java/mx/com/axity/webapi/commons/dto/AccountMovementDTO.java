package mx.com.axity.webapi.commons.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import mx.com.axity.webapi.commons.enums.MovementType;

/***
 * Data transfer object for account movement.
 * 
 * @author guillermo.segura@axity.com
 */
public class AccountMovementDTO
{
  private Integer id;
  private Integer accountId;
  private MovementType movementType;
  private BigDecimal amount;
  private LocalDateTime movementTimestamp;
  /**
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId( Integer id )
  {
    this.id = id;
  }

  /**
   * @return the accountId
   */
  public Integer getAccountId()
  {
    return accountId;
  }

  /**
   * @param accountId the accountId to set
   */
  public void setAccountId( Integer accountId )
  {
    this.accountId = accountId;
  }

  /**
   * @return the movementType
   */
  public MovementType getMovementType()
  {
    return movementType;
  }

  /**
   * @param movementType the movementType to set
   */
  public void setMovementType( MovementType movementType )
  {
    this.movementType = movementType;
  }

  /**
   * @return the amount
   */
  public BigDecimal getAmount()
  {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount( BigDecimal amount )
  {
    this.amount = amount;
  }

  /**
   * @return the movementTimestamp
   */
  public LocalDateTime getMovementTimestamp()
  {
    return movementTimestamp;
  }

  /**
   * @param movementTimestamp the movementTimestamp to set
   */
  public void setMovementTimestamp( LocalDateTime movementTimestamp )
  {
    this.movementTimestamp = movementTimestamp;
  }

}
