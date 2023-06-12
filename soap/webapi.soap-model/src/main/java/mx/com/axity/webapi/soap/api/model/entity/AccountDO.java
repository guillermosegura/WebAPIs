package mx.com.axity.webapi.soap.api.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import mx.com.axity.webapi.soap.api.model.base.AbstractSignedBaseDO;

/***
 * Entity class for table TE_Account.
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TE_Account")
public class AccountDO extends AbstractSignedBaseDO
{
  private static final long serialVersionUID = -7016988299960596670L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cd_person", referencedColumnName = "cd_id")
  private PersonDO person;

  @Column(name = "im_balance")
  private BigDecimal balance;

  @Column(name = "ts_created")
  private Date createdTimestamp;

  @Column(name = "ts_updated")
  private Date updatedTimestamp;

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
   * @return the person
   */
  public PersonDO getPerson()
  {
    return person;
  }

  /**
   * @param person the person to set
   */
  public void setPerson( PersonDO person )
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
   * @return the createdTimestamp
   */
  public Date getCreatedTimestamp()
  {
    return createdTimestamp;
  }

  /**
   * @param createdTimestamp the createdTimestamp to set
   */
  public void setCreatedTimestamp( Date createdTimestamp )
  {
    this.createdTimestamp = createdTimestamp;
  }

  /**
   * @return the updatedTimestamp
   */
  public Date getUpdatedTimestamp()
  {
    return updatedTimestamp;
  }

  /**
   * @param updatedTimestamp the updatedTimestamp to set
   */
  public void setUpdatedTimestamp( Date updatedTimestamp )
  {
    this.updatedTimestamp = updatedTimestamp;
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
      AccountDO that = (AccountDO) object;

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
