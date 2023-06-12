package mx.com.axity.webapi.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.AccountDTO;
import mx.com.axity.webapi.soap.api.service.AccountService;
import mx.com.axity.webapi.soap.api.util.account.GetAccountResponseFactory;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountRequest;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse;

@Endpoint
public class AccountEndpoint {
  private static final String NAMESPACE_URI = "http://axity.com.mx/webapi/soap/api/ws/account";

  @Autowired
  private AccountService accountService;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccountRequest")
  @ResponsePayload
  public GetAccountResponse getAccount(@RequestPayload GetAccountRequest request) {
    GetAccountResponse response = null;
    try {
      ResponseWrapperDTO<AccountDTO> account = accountService.getAccount(request.getId());
      response = GetAccountResponseFactory.transform(account);
    } catch (Exception e) {
      response = GetAccountResponseFactory.transform(e);
    }

    return response;
  }

}
