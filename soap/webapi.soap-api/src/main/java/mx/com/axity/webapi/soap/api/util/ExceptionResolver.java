package mx.com.axity.webapi.soap.api.util;

import javax.xml.namespace.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import mx.com.axity.webapi.soap.api.commons.exception.BusinessException;

public class ExceptionResolver extends SoapFaultMappingExceptionResolver {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

  private static final QName CODE = new QName("code");
  private static final QName MESSAGE = new QName("message");
  private static final QName DETAILS = new QName("details");

  @Override
  protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
    logger.error("Exception processed ", ex);
    if (ex instanceof BusinessException) {
      BusinessException businessException = (BusinessException) ex;
      SoapFaultDetail detail = fault.addFaultDetail();
      detail.addFaultDetailElement(CODE).addText(businessException.getCode() + "");
      detail.addFaultDetailElement(MESSAGE).addText(this.getMesage(businessException));
      if (businessException.getDetails() != null) {
        detail.addFaultDetailElement(DETAILS).addText(businessException.getDetails());
      }

    } else {
      SoapFaultDetail detail = fault.addFaultDetail();
      detail.addFaultDetailElement(CODE).addText("1");
      detail.addFaultDetailElement(MESSAGE).addText(this.getMesage(ex));
    }
  }

  private String getMesage(Exception ex) {
    String message = ex.getMessage();
    if (message == null) {
      message = ex.getClass().getCanonicalName();
    }

    return message;
  }
}
