package mx.com.axity.webapi.model.entity;

import mx.com.axity.webapi.model.base.AbstractBaseDO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "TH_AccountMovement")
public class AccountMovementDO extends AbstractBaseDO
{
  private static final long serialVersionUID = -5540349490421740761L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cd_account", referencedColumnName = "cd_id")
  private AccountDO account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cd_type", referencedColumnName = "cd_id")
  private MovementTypeDO movementType;

  @Column(name = "im_amount", nullable = false)
  private BigDecimal amount;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "ts_movement", nullable = false)
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
   * @return the account
   */
  public AccountDO getAccount()
  {
    return account;
  }

  /**
   * @param account the account to set
   */
  public void setAccount( AccountDO account )
  {
    this.account = account;
  }

  /**
   * @return the movementType
   */
  public MovementTypeDO getMovementType()
  {
    return movementType;
  }

  /**
   * @param movementType the movementType to set
   */
  public void setMovementType( MovementTypeDO movementType )
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      AccountMovementDO that = (AccountMovementDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
