package mx.axity.com.webapi.rest.commons;

import java.io.Serializable;

/**
 * DTO class for handling the error messages
 * 
 * @author guillermo.segura@axity.com
 *
 */
public class ErrorDetailsDTO implements Serializable {

  private static final long serialVersionUID = 7004694942552360990L;
  private String errorCode;
  private String errorMessage;
  private String userError;
  private String info;

  /**
   * Constructor default.
   */
  public ErrorDetailsDTO() {}

  /**
   * Constructor by porperties
   * 
   * @param errorCode
   * @param errorMessage
   * @param userError
   * @param info
   */
  public ErrorDetailsDTO(String errorCode, String errorMessage, String userError, String info) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.userError = userError;
    this.info = info;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCode the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * @return the errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * @param errorMessage the errorMessage to set
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * @return the userError
   */
  public String getUserError() {
    return userError;
  }

  /**
   * @param userError the userError to set
   */
  public void setUserError(String userError) {
    this.userError = userError;
  }

  /**
   * @return the info
   */
  public String getInfo() {
    return info;
  }

  /**
   * @param info the info to set
   */
  public void setInfo(String info) {
    this.info = info;
  }

}
