package mx.axity.com.webapi.soap.client.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountRequest;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse;

/**
 * Implementation class for the Account webservice client.
 * 
 * @author guillermo.segura@axity.com
 *
 */
public class AccountClient extends WebServiceGatewaySupport {

  /**
   * Gets an account
   * 
   * @param request
   * @return
   */
  public GetAccountResponse getAccount(GetAccountRequest request) {
    GetAccountResponse response = (GetAccountResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return response;
  }

}
