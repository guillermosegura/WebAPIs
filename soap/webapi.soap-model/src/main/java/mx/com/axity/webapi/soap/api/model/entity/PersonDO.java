package mx.com.axity.webapi.soap.api.model.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import mx.com.axity.webapi.soap.api.model.base.AbstractSignedBaseDO;

/***
 * Entity class for table TC_Person.
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TC_Person")
public class PersonDO extends AbstractSignedBaseDO
{
  private static final long serialVersionUID = 3533149152191391287L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @Column(name = "nb_name", nullable = false)
  private String name;

  @Column(name = "nb_lastname", nullable = false)
  private String lastname;

  @Column(name = "nb_email", nullable = false)
  private String email;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
  private List<AccountDO> accounts;

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
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName( String name )
  {
    this.name = name;
  }

  /**
   * @return the lastname
   */
  public String getLastname()
  {
    return lastname;
  }

  /**
   * @param lastname the lastname to set
   */
  public void setLastname( String lastname )
  {
    this.lastname = lastname;
  }

  /**
   * @return the email
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail( String email )
  {
    this.email = email;
  }

  /**
   * @return the accounts
   */
  public List<AccountDO> getAccounts()
  {
    return accounts;
  }

  /**
   * @param accounts the accounts to set
   */
  public void setAccounts( List<AccountDO> accounts )
  {
    this.accounts = accounts;
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
      PersonDO that = (PersonDO) object;

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
