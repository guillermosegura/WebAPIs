package mx.axity.com.webapi.soap.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import mx.axity.com.webapi.soap.client.ws.AccountClient;

/**
 * Configuration file for {@link mx.axity.com.webapi.soap.client.ws.AccountClient}
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Configuration
public class AccountClientConfig {

  @Bean(name = "accountMarshaller")
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("mx.com.axity.webapi.soap.api.ws.account");
    return marshaller;
  }

  @Bean
  public AccountClient accountClient() {
    AccountClient client = new AccountClient();
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller());
    client.setUnmarshaller(marshaller());
    return client;
  }
}
