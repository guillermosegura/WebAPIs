package mx.axity.com.webapi.soap.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import mx.axity.com.webapi.soap.client.ws.PersonClient;

/**
 * Configuration file for {@link mx.axity.com.webapi.soap.client.ws.PersonClient}
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Configuration
public class PersonClientConfig {

  @Bean(name = "personMarshaller")
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("mx.com.axity.webapi.soap.api.ws.person");
    return marshaller;
  }

  @Bean
  public PersonClient personClient() {
    PersonClient client = new PersonClient();
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller());
    client.setUnmarshaller(marshaller());
    return client;
  }
}
