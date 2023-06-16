package mx.axity.com.webapi.rest.commons.exception;

public class CommonBaseException extends RuntimeException {
  private static final long serialVersionUID = -5227294959754345325L;
  private int code;

  /**
   * Creates a new instance of CommonBaseException with the specified error code.
   *
   * @param code the error code associated with the exception
   */
  public CommonBaseException(int code) {
    this.code = code;
  }

  /**
   * Creates a new instance of CommonBaseException with the specified message.
   *
   * @param message the error message
   */
  public CommonBaseException(String message) {
    super(message);
  }

  /**
   * Creates a new instance of CommonBaseException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public CommonBaseException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new instance of CommonBaseException with the specified message and cause.
   *
   * @param message the error message
   * @param cause the cause of the exception
   */
  public CommonBaseException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance of CommonBaseException with the specified message and code.
   *
   * @param message the error message
   * @param code the error code associated with the exception
   */
  public CommonBaseException(String message, int code) {
    super(message);
    this.code = code;
  }

  /**
   * Creates a new instance of CommonBaseException with the specified message, cause, and code.
   *
   * @param message the error message
   * @param cause the cause of the exception
   * @param code the error code associated with the exception
   */
  public CommonBaseException(String message, Throwable cause, int code) {
    super(message, cause);
    this.code = code;
  }

  /**
   * Creates a new instance of CommonBaseException with the specified cause and code.
   *
   * @param cause the cause of the exception
   * @param code the error code associated with the exception
   */
  public CommonBaseException(Throwable cause, int code) {
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
