package mx.com.axity.webapi.commons.dto;

import java.io.Serializable;

/**
 * Data transfer object for person.
 * 
 * @author guillermo.segura@axity.com
 */
public class PersonDTO implements Serializable
{

  private static final long serialVersionUID = -2101754779529560838L;
  private Integer id;
  private String name;
  private String lastname;
  private String email;
  private boolean active;
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
   * @return the active
   */
  public boolean isActive()
  {
    return active;
  }

  /**
   * @param active the active to set
   */
  public void setActive( boolean active )
  {
    this.active = active;
  }

}
