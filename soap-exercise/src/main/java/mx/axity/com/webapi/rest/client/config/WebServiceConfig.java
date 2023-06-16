package mx.axity.com.webapi.rest.client.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
  private static final String NAMESPACE_URI_PLAYER = "com.axity.dnd";

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
  @Bean(name = "player")
  public DefaultWsdl11Definition personWsdl11Definition(XsdSchema playerSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PlayerPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace(NAMESPACE_URI_PLAYER);
    wsdl11Definition.setSchema(playerSchema);
    return wsdl11Definition;
  }
  
  /**
   * Defines the schema location for person.
   * 
   * @return
   */
  @Bean
  public XsdSchema personSchema() {
    return new SimpleXsdSchema(new ClassPathResource("xsd/player.xsd"));
  }
}
