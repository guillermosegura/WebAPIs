package mx.axity.com.webapi.rest.commons.exception;

/**
 * Custom exception to represent business errors. Extends RuntimeException to avoid the need to
 * declare it in the method signature or throw block.
 * 
 * @author guillermo.segura@axity.com
 */
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = -7021338792240031291L;
  private int code;

  /**
   * Creates a new instance of BusinessException with the specified error code.
   *
   * @param code the error code associated with the exception
   */
  public BusinessException(int code) {
    this.code = code;
  }

  /**
   * Creates a new instance of BusinessException with the specified message.
   *
   * @param message the error message
   */
  public BusinessException(String message) {
    super(message);
  }

  /**
   * Creates a new instance of BusinessException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public BusinessException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new instance of BusinessException with the specified message and cause.
   *
   * @param message the error message
   * @param cause the cause of the exception
   */
  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance of BusinessException with the specified message and code.
   *
   * @param message the error message
   * @param code the error code associated with the exception
   */
  public BusinessException(String message, int code) {
    super(message);
    this.code = code;
  }

  /**
   * Creates a new instance of BusinessException with the specified message, cause, and code.
   *
   * @param message the error message
   * @param cause the cause of the exception
   * @param code the error code associated with the exception
   */
  public BusinessException(String message, Throwable cause, int code) {
    super(message, cause);
    this.code = code;
  }

  /**
   * Creates a new instance of BusinessException with the specified cause and code.
   *
   * @param cause the cause of the exception
   * @param code the error code associated with the exception
   */
  public BusinessException(Throwable cause, int code) {
    super(cause);
    this.code = code;
  }

  /**
   * Get the error code associated with the exception.
   *
   * @return the error code
   */
  public int getCode() {
    return code;
  }
}

