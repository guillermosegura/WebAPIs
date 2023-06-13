package mx.axity.com.webapi.rest.config;

import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuration class for setting up validation in the application.
 * 
 * <p>
 * This class provides a bean definition for the Validator interface, using the
 * LocalValidatorFactoryBean implementation. By configuring the Validator bean, it enables
 * validation support in the application.
 * </p>
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Configuration
public class ValidationConfig {

  /**
   * Bean definition for the Validator interface.
   * 
   * <p>
   * This method creates and configures an instance of the LocalValidatorFactoryBean, which implements
   * the Validator interface. The LocalValidatorFactoryBean is the recommended implementation for
   * JSR-303 (Bean Validation) in Spring applications.
   * </p>
   * 
   * @return the configured Validator bean.
   */
  @Bean
  public Validator validator() {
    return new LocalValidatorFactoryBean();
  }

}
