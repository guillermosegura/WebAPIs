package mx.axity.com.webapi.soap.client.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
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
import mx.axity.com.webapi.soap.client.config.PersonClientConfig;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse;

@ContextConfiguration(classes = PersonClientConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class PersonClientTest {
  private static final Logger logger = LoggerFactory.getLogger(PersonClientTest.class);

  @Autowired
  private PersonClient personClient;

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5})
  void testGetPerson(int personId) {
    GetPersonRequest request = new GetPersonRequest();
    request.setId(personId);

    GetPersonResponse response = this.personClient.getPerson(request);
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals(personId, response.getBody().getId());

    logger.info("Id: {} - {}, {} email:{}", response.getBody().getId(), response.getBody().getLastname(),
        response.getBody().getName(), response.getBody().getEmail());

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
    //assertFalse(response.getBody().getItems().getPerson().isEmpty());

    logger.info("size: {}, offset: {}", size, offset);
    response.getBody().getItems().getPerson().stream().forEach(person -> {
      logger.info("Id: {} - {}, {} email:{}", person.getId(), person.getLastname(), person.getName(),
          person.getEmail());
    });
  }

}