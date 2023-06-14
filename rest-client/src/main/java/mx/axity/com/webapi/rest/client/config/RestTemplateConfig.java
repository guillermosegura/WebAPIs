package mx.axity.com.webapi.rest.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import mx.axity.com.webapi.rest.client.service.RestTemplateProvider;

/**
 * Configuration class for RestTemplate bean creation.
 * 
 * <p>
 * This class provides a bean definition for creating a RestTemplate instance, which is a convenient
 * class for making HTTP requests in Spring applications.
 * </p>
 * 
 * <p>
 * The RestTemplate bean can be injected into other components and used to interact with RESTful
 * services.
 * </p>
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Configuration
public class RestTemplateConfig {
  /**
   * Creates a RestTemplate bean instance.
   * 
   * @return the RestTemplate bean
   */
  @Bean
  public RestTemplateProvider restTemplateProvider() {
    return new RestTemplateProvider();
  }
}
