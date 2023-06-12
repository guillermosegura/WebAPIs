package mx.com.axity.webapi.soap.api.util.account;

import mx.com.axity.webapi.soap.api.commons.base.HeaderDTO;
import mx.com.axity.webapi.soap.api.commons.exception.BusinessException;
import mx.com.axity.webapi.soap.api.ws.account.Header;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.account.Header}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class HeaderFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.HeaderDTO} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.Header}
   * 
   * @param headerDTO
   * @return
   */
  public static Header transform(HeaderDTO headerDTO) {
    Header header = new Header();
    header.setCode(headerDTO.getCode());
    header.setMessage(headerDTO.getMessage());
    header.setDetails(header.getDetails());
    return header;
  }

  /**
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.Header}
   * 
   * @param e
   * @return
   */
  public static Header transform(Exception e) {
    if (e instanceof BusinessException) {
      return transformBusinessException((BusinessException) e);
    }

    return transformException(e);
  }

  private static Header transformBusinessException(BusinessException businessException) {
    Header header = new Header();
    header.setCode(businessException.getCode());
    header.setMessage(businessException.getMessage());
    header.setDetails(businessException.getDetails());
    return header;
  }

  private static Header transformException(Exception e) {
    Header header = new Header();
    header.setCode(1);
    header.setMessage(e.getMessage());
    header.setDetails(e.getLocalizedMessage());
    return header;
  }
}
