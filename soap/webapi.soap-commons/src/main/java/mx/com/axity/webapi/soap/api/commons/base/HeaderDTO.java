package mx.com.axity.webapi.soap.api.commons.base;

import java.io.Serializable;

/**
 * Header class.
 * 
 * @author guillermo.segura@axity.com
 */
public class HeaderDTO implements Serializable
{
  private static final long serialVersionUID = -7276971886309979114L;
  private int code;
  private String message;
  private String details;

  /**
   * Default constructor.
   */
  public HeaderDTO()
  {
  }

  /**
   * COnstructor by code, message, details.
   * 
   * @param code
   * @param message
   * @param details
   */
  public HeaderDTO( int code, String message, String details )
  {
    this.code = code;
    this.message = message;
    this.details = details;
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode( int code )
  {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage( String message )
  {
    this.message = message;
  }

  /**
   * @return the details
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * @param details the details to set
   */
  public void setDetails( String details )
  {
    this.details = details;
  }

}
