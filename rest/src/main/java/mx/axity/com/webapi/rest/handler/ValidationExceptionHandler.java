package mx.axity.com.webapi.rest.handler;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import mx.axity.com.webapi.rest.commons.dto.ErrorDetailsDTO;


@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

//  @Autowired
//  private ResourceBundleMessageSource messageSource;

  /**
   * {@inheritDoc}
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

    ErrorDetailsDTO body = new ErrorDetailsDTO();

    body.setErrorCode("1");
    body.setErrorMessage(String.join(",", errors));
//    body.setUserError(messageSource.getMessage("error.1", null, Locale.getDefault()));


    return new ResponseEntity<>(body, headers, status);

  }
}
