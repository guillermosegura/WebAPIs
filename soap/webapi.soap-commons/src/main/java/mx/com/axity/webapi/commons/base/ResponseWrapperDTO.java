package mx.com.axity.webapi.commons.base;

import java.io.Serializable;

import mx.com.axity.webapi.commons.dto.AccountDTO;

/**
 * Response wrapper class.
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 */
public class ResponseWrapperDTO<T> implements Serializable
{
  private static final long serialVersionUID = 5278266388422332009L;
  private HeaderDTO header;
  private T body;

  /**
   * Default constructor.
   */
  public ResponseWrapperDTO()
  {
  }

  /**
   * Constructor by header and body.
   * 
   * @param header
   * @param body
   */
  public ResponseWrapperDTO( HeaderDTO header, T body )
  {
    this.header = header;
    this.body = body;
  }

  /**
   * @return the header
   */
  public HeaderDTO getHeader()
  {
    return header;
  }

  /**
   * @param header the header to set
   */
  public void setHeader( HeaderDTO header )
  {
    this.header = header;
  }

  /**
   * @return the body
   */
  public T getBody()
  {
    return body;
  }

  /**
   * @param body the body to set
   */
  public void setBody( T body )
  {
    this.body = body;
  }
}
