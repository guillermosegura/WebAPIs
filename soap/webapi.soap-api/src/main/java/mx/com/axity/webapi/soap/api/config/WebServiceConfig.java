package mx.com.axity.webapi.soap.api.config;

import java.util.Properties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import mx.com.axity.webapi.soap.api.commons.exception.BusinessException;
import mx.com.axity.webapi.soap.api.util.ExceptionResolver;

/**
 * Webservice configuration for soap endpoints.
 * 
 * @author guillermo.segura@axity.com
 *
 */
@EnableWs
@Configuration
public class WebServiceConfig {

  private static final String NAMESPACE_URI_PERSON = "http://axity.com.mx/webapi/soap/api/ws/person";

  private static final String NAMESPACE_URI_ACCOUNT = "http://axity.com.mx/webapi/soap/api/ws/account";

  /**
   * Creates the servlet for endpoints /ws/*
   * 
   * @param applicationContext
   * @return
   */
  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
  }

  /**
   * Defines the bean person for PersonPort.
   * 
   * @param personSchema
   * @return
   */
  @Bean(name = "person")
  public DefaultWsdl11Definition personWsdl11Definition(XsdSchema personSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PersonPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace(NAMESPACE_URI_PERSON);
    wsdl11Definition.setSchema(personSchema);
    return wsdl11Definition;
  }

  /**
   * Defines the schema location for person.
   * 
   * @return
   */
  @Bean
  public XsdSchema personSchema() {
    return new SimpleXsdSchema(new ClassPathResource("xsd/person.xsd"));
  }


  /**
   * Defines the bean person for AccountPort.
   * 
   * @param accountSchema
   * @return
   */
  @Bean(name = "account")
  public DefaultWsdl11Definition accountWsdl11Definition(XsdSchema accountSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("AccountPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace(NAMESPACE_URI_ACCOUNT);
    wsdl11Definition.setSchema(accountSchema);
    return wsdl11Definition;
  }

  /**
   * Defines the schema location for account.
   * 
   * @return
   */
  @Bean
  public XsdSchema accountSchema() {
    return new SimpleXsdSchema(new ClassPathResource("xsd/account.xsd"));
  }

  @Bean
  public SoapFaultMappingExceptionResolver exceptionResolver() {
    SoapFaultMappingExceptionResolver exceptionResolver = new ExceptionResolver();

    SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
    faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
    exceptionResolver.setDefaultFault(faultDefinition);

    Properties errorMappings = new Properties();
    errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
    errorMappings.setProperty(BusinessException.class.getName(), SoapFaultDefinition.SERVER.toString());
    exceptionResolver.setExceptionMappings(errorMappings);
    exceptionResolver.setOrder(1);
    return exceptionResolver;
  }
}
