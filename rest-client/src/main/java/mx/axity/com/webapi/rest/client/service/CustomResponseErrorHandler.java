package mx.axity.com.webapi.rest.client.service;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

  private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
      return errorHandler.hasError(response);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
      HttpStatusCode statusCode = response.getStatusCode();
      // Ignore specific HTTP error codes that you don't want to throw exceptions for
      if (statusCode != HttpStatus.NOT_FOUND && statusCode != HttpStatus.INTERNAL_SERVER_ERROR) {
          // Delegate handling to the default error handler
          errorHandler.handleError(response);
      }
      // You can also handle specific error codes here if needed
  }

}
