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
import mx.axity.com.webapi.rest.commons.ErrorDetailsDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;

/**
 * Aspect Json Response Handler Interceptor class
 * 
 * @author username@axity.com
 */
@Aspect
@Component
public class JsonResponseHandlerInterceptor implements HandlerInterceptor {
  private static final Logger LOG = LoggerFactory.getLogger(JsonResponseHandlerInterceptor.class);

  @Autowired
  private ResourceBundleMessageSource messageSource;

  /**
   * 
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
