package mx.com.axity.webapi.soap.api.commons.exception;

/***
 * The business excpetion class.
 * 
 * @author guillermo.segura@axity.com
 */
public class BusinessException extends RuntimeException
{
  private static final long serialVersionUID = -3229703915184559004L;
  private int code;
  private String details;

  /***
   * Default constructor.
   */
  public BusinessException()
  {
    super();
    this.code = 1;
  }

  /**
   * Constructor by message.
   * 
   * @param message
   */
  public BusinessException( String message )
  {
    super( message );
    this.code = 1;
  }

  /**
   * Constructor by throwable cause.
   * 
   * @param message
   */
  public BusinessException( Throwable cause )
  {
    super( cause );
    this.code = 1;
  }

  /**
   * Constructor by message and throwable cause.
   * 
   * @param message
   * @param cause
   */
  public BusinessException( String message, Throwable cause )
  {
    super( message, cause );
    this.code = 1;
  }

  /**
   * Constructor by code and message.
   * 
   * @param code
   * @param message
   */
  public BusinessException( int code, String message )
  {
    super(message);
    this.code = code;
  }

  /**
   * Constructor by message, code and details.
   * 
   * @param message
   * @param code
   * @param details
   */
  public BusinessException( int code, String message, String details )
  {
    super( message );
    this.code = code;
    this.details = details;
  }

  /**
   * Constructor by message, throwable cause, code and details.
   * 
   * @param code
   * @param message
   * @param details
   * @param cause
   */
  public BusinessException( int code, String message, String details, Throwable cause )
  {
    super( message, cause );
    this.code = code;
    this.details = details;
  }

  /**
   * Constructor by throwable cause, code and details.
   * 
   * @param message
   * @param code
   * @param details
   */
  public BusinessException( int code, String message, Throwable cause )
  {
    super( message, cause );
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

  /**
   * @return the details
   */
  public String getDetails()
  {
    return details;
  }

}
