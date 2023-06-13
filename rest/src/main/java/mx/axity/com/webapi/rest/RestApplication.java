package mx.axity.com.webapi.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for starting the REST application.
 * 
 * <p>
 * This class contains the main method that is responsible for starting the REST application by
 * running the Spring Boot application. It is annotated with the {@code @SpringBootApplication}
 * annotation, which enables auto-configuration and component scanning features of Spring Boot.
 * </p>
 */
@SpringBootApplication
public class RestApplication {
  /**
   * The main method that starts the REST application.
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }

}
