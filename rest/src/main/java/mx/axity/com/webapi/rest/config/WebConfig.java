package mx.axity.com.webapi.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up web-related configurations in the application.
 * 
 * <p>
 * This class implements the WebMvcConfigurer interface, allowing customization of Spring MVC
 * configuration. It provides a bean definition for the ResourceBundleMessageSource, which is
 * responsible for resolving message codes to actual messages from resource bundles.
 * </p>
 * 
 * <p>
 * The configured ResourceBundleMessageSource uses the "message" basename to locate the resource
 * bundle, and sets the default encoding to UTF-8 for proper character encoding of messages.
 * </p>
 *
 * <p>
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  /**
   * Bean definition for the ResourceBundleMessageSource.
   * 
   * <p>
   * This method creates and configures an instance of the ResourceBundleMessageSource, which is
   * responsible for resolving message codes to actual messages from resource bundles.
   * </p>
   * 
   * <p>
   * The configured ResourceBundleMessageSource uses the "message" basename to locate the resource
   * bundle, and sets the default encoding to UTF-8 for proper character encoding of messages.
   * </p>
   * 
   * @return the configured ResourceBundleMessageSource bean.
   */
  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("message");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

}
