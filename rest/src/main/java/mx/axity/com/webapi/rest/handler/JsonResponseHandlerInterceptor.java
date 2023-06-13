package mx.axity.com.webapi.rest.handler;

import java.util.Locale;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import mx.axity.com.webapi.rest.commons.dto.ErrorDetailsDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;

/**
 * Aspect class for handling JSON response in the interceptor.
 * 
 * <p>
 * This class is an interceptor that handles the JSON response in the controller methods annotated
 * with the {@code @JsonResponseInterceptor} annotation. It is responsible for intercepting the
 * method execution, handling any exceptions that occur, and building the appropriate error
 * response.
 * </p>
 * 
 * <p>
 * This class is annotated with the {@code @Aspect} and {@code @Component} annotations to enable
 * Aspect-oriented Programming (AOP) and mark it as a Spring component.
 * </p>
 * 
 * <p>
 * The main method in this class is {@code interceptMethodAdvice}, which is advised around the
 * execution of controller methods annotated with {@code @JsonResponseInterceptor}. It intercepts
 * the method execution, logs the method details, and proceeds with the method execution. If any
 * exceptions occur during the method execution, it builds an error response and returns it.
 * </p>
 * 
 * <p>
 * This class also depends on the {@code ResourceBundleMessageSource} bean for retrieving error
 * messages. The error details are constructed using the {@code createrErrorDetails} method, which
 * extracts the error message, error code, and user-friendly error message from the exception.
 * </p>
 * 
 * <p>
 * Note: The exact logic and implementation of this class may vary based on the specific
 * requirements of the project.
 * </p>
 * 
 * @see mx.axity.com.webapi.rest.aspectj.JsonResponseInterceptor
 * 
 * @author guillermo.segura@axity.com
 */
@Aspect
@Component
public class JsonResponseHandlerInterceptor implements HandlerInterceptor {
  private static final Logger LOG = LoggerFactory.getLogger(JsonResponseHandlerInterceptor.class);

  @Autowired
  private ResourceBundleMessageSource messageSource;

  /**
   * Intercepts the execution of controller methods annotated with {@code @JsonResponseInterceptor}.
   * 
   * @param pjp the proceeding join point for the intercepted method
   * @return the result of the intercepted method
   * @throws Throwable if an error occurs during method execution
   */
  @Around("execution (* mx.axity.com.webapi.rest.controller.*.*(..))"
      + " and @annotation(mx.axity.com.webapi.rest.aspectj.JsonResponseInterceptor)")
  public Object interceptMethodAdvice(ProceedingJoinPoint pjp) throws Throwable {
    Object result = null;
    try {
      LOG.debug("{}", pjp.toLongString());
      result = pjp.proceed();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      // Builds the generic error JSON object
      ErrorDetailsDTO errorDetails = createrErrorDetails(e);

      result = new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return result;
  }

  private ErrorDetailsDTO createrErrorDetails(Exception ex) {
    ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
    errorDetails.setErrorMessage(ex.getMessage());

    String errorCode;
    String userError;
    if (ex instanceof BusinessException) {
      BusinessException be = (BusinessException) ex;

      userError = messageSource.getMessage("error." + be.getCode(), null, Locale.getDefault());
      errorCode = "" + be.getCode();
      errorDetails.setUserError(userError);

    } else {
      userError = messageSource.getMessage("error.0", null, Locale.getDefault());
      errorCode = "0";
    }
    errorDetails.setErrorCode(errorCode);
    errorDetails.setUserError(userError);
    return errorDetails;
  }

}
