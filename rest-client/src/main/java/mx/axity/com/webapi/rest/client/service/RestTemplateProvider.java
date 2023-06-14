package mx.axity.com.webapi.rest.client.service;

import org.springframework.web.client.RestTemplate;

/**
 * Provider class for obtaining a customized instance of RestTemplate.
 * 
 * @author guillermo.segura@axity.com
 *
 */
public class RestTemplateProvider {

  /**
   * Get an instance of RestTemplate with a custom response error handler.
   *
   * @return The configured RestTemplate instance.
   */
  public RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new CustomResponseErrorHandler());

    return restTemplate;
  }
}
