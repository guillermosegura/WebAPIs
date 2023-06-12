package mx.axity.com.webapi.soap.client.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import mx.axity.com.webapi.soap.client.config.AccountClientConfig;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountRequest;
import mx.com.axity.webapi.soap.api.ws.account.GetAccountResponse;

@ContextConfiguration(classes = AccountClientConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class AccountClientTest {
  private static final Logger logger = LoggerFactory.getLogger(AccountClientTest.class);

  @Autowired
  private AccountClient accountClient;


  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5})
  void testGetAccount(int accountId) {
    GetAccountRequest request = new GetAccountRequest();
    request.setId(accountId);

    GetAccountResponse response = this.accountClient.getAccount(request);

    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals(accountId, response.getBody().getId());
    
    logger.info("Account {}, balance {}, owner {}, {}", response.getBody().getId(), response.getBody().getBalance(), response.getBody().getPerson().getLastname(), response.getBody().getPerson().getName());

  }

}
