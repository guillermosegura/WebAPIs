package mx.com.axity.webapi.soap.api.util.account;

import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.AccountDTO;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse;

/**
 * Factory method for {@link mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class GetAccountResponseFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO<AccountDTO>}
   * into a {@link mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse}
   * 
   * @param response
   * @return
   */
  public static GetAccountResponse transform(ResponseWrapperDTO<AccountDTO> response) {
    GetAccountResponse wrapper = new GetAccountResponse();
    wrapper.setHeader(HeaderFactory.transform(response.getHeader()));
    wrapper.setBody(AccountFactory.transform(response.getBody()));

    return wrapper;
  }

  /***
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse}
   * 
   * @param e
   * @return
   */
  public static GetAccountResponse transform(Exception e) {
    GetAccountResponse wrapper = new GetAccountResponse();
    wrapper.setHeader(HeaderFactory.transform(e));
    return wrapper;
  }


}
