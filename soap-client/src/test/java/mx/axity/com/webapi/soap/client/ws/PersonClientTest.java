package mx.axity.com.webapi.soap.client.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import mx.axity.com.webapi.soap.client.config.PersonClientConfig;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse;
import mx.com.axity.webapi.soap.api.ws.person.Header;
import mx.com.axity.webapi.soap.api.ws.person.Person;

@ContextConfiguration(classes = PersonClientConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class PersonClientTest {
  private static final Logger logger = LoggerFactory.getLogger(PersonClientTest.class);
  private static final QName CODE = new QName("code");
  private static final QName MESSAGE = new QName("message");
  private static final QName DETAILS = new QName("details");


  @Autowired
  private PersonClient personClient;


  private WebServiceTemplate mocked = mock(WebServiceTemplate.class);

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(personClient, "webServiceTemplate", mocked);
  }


  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5})
  void testGetPerson(int personId) {
    GetPersonRequest request = new GetPersonRequest();
    request.setId(personId);

    Map<Integer, Person> map = new HashMap<>();
    map.put(1, createPerson(1, "Juan", "Perez", "juan.perez@email.com", true));
    map.put(2, createPerson(2, "Jose", "Perez", "jose.perez@email.com", true));
    map.put(3, createPerson(3, "Julio", "Perez", "julio.perez@email.com", true));
    map.put(4, createPerson(4, "Joaquin", "Perez", "joaquin.perez@email.com", true));
    map.put(5, createPerson(5, "Jimena", "Perez", "jimena.perez@email.com", true));
    
    GetPersonResponse mockedResponse = new GetPersonResponse();
    mockedResponse.setHeader(new Header());
    mockedResponse.getHeader().setCode(0);
    mockedResponse.setBody(map.get(personId));



    when(mocked.marshalSendAndReceive(any(GetPersonRequest.class))).thenReturn(mockedResponse);

    GetPersonResponse response = this.personClient.getPerson(request);
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals(personId, response.getBody().getId());

    logger.info("Id: {} - {}, {} email:{}", response.getBody().getId(), response.getBody().getLastname(),
        response.getBody().getName(), response.getBody().getEmail());

  }

  @Test()
  void testGetPerson_notFound() {
    GetPersonRequest request = new GetPersonRequest();
    request.setId(9999);

    SoapFaultClientException soapFaultClientException =
        assertThrows(SoapFaultClientException.class, () -> this.personClient.getPerson(request));

    logger.info("Ocurrio un error: {}", soapFaultClientException.getFaultStringOrReason());
    assertNotNull(soapFaultClientException);
  }

  @ParameterizedTest
  @CsvSource({"5,0", "5,5", "5,10", "5,15"})
  void testGetPersons(int size, int offset) {
    GetPersonsRequest request = new GetPersonsRequest();
    request.setSize(size);
    request.setOffset(offset);

    GetPersonsResponse response = this.personClient.getPersons(request);
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    // assertFalse(response.getBody().getItems().getPerson().isEmpty());

    logger.info("size: {}, offset: {}", size, offset);
    response.getBody().getItems().getPerson().stream().forEach(person -> {
      logger.info("Id: {} - {}, {} email:{}", person.getId(), person.getLastname(), person.getName(),
          person.getEmail());
    });
  }

  private static Person createPerson(int id, String name, String lastname, String email, boolean active) {
    Person person = new Person();
    person.setId(id);
    person.setName(name);
    person.setLastname(lastname);
    person.setEmail(email);
    return person;
  }
}
