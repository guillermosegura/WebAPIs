package mx.com.axity.webapi.soap.api.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mx.com.axity.webapi.soap.api.model.base.AbstractBaseDO;


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
  private Date movementTimestamp;
  
  
  
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
  public Date getMovementTimestamp()
  {
    return movementTimestamp;
  }

  /**
   * @param movementTimestamp the movementTimestamp to set
   */
  public void setMovementTimestamp( Date movementTimestamp )
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
