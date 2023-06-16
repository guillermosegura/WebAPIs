package mx.axity.com.webapi.rest.client.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mx.axity.com.webapi.rest.client.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.client.commons.dto.ErrorDetailsDTO;
import mx.axity.com.webapi.rest.client.commons.util.BookFactory;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)

class BookClientServiceTest {
  private static final Logger logger = LoggerFactory.getLogger(BookClientServiceTest.class);
  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @MockBean
  private RestTemplateProvider restTemplateProvider;

  @Autowired
  private BookClientService bookClientService;

  @Test
  void testCreate() {
    BookDTO created = BookFactory.createBook("The House of the Spirits", "Isabel Allende", "Magical Realism");
    created.setId(21);

    RestTemplate restTemplate = mock(RestTemplate.class);
    when(restTemplateProvider.getRestTemplate()).thenReturn(restTemplate);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION, "/api/books/" + created.getId());
    headers.add("X-Value", UUID.randomUUID().toString());

    ResponseEntity<String> value =
        ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(gson.toJson(created));

    when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(value);

    BookDTO book = BookFactory.createBook("The House of the Spirits", "Isabel Allende", "Magical Realism");
    book = bookClientService.create(book);

    assertNotNull(book);
    assertEquals(21, book.getId());
    assertEquals("The House of the Spirits", book.getTitle());
    assertEquals("Isabel Allende", book.getAuthor());
    assertEquals("Magical Realism", book.getGenre());
    logger.info("{}", gson.toJson(book));

  }

  @Test
  void testCreate_ValidationError() {
    ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO();
    errorDetailsDTO.setErrorCode("1");
    errorDetailsDTO.setErrorMessage("Validation errors");
    errorDetailsDTO.setUserError("Errores de validacion");

    RestTemplate restTemplate = mock(RestTemplate.class);
    when(restTemplateProvider.getRestTemplate()).thenReturn(restTemplate);

    HttpHeaders headers = new HttpHeaders();

    ResponseEntity<String> value =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(gson.toJson(errorDetailsDTO));

    when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(value);

    BookDTO book = BookFactory.createBook("The House of the Spirits", "Isabel Allende", "Magical Realism");
    book = bookClientService.create(book);
    assertNull(book);

  }
  
  @Test
  void testCreate_BusinessException() {
    ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO();
    errorDetailsDTO.setErrorCode("100");
    errorDetailsDTO.setErrorMessage("Registry found");
    errorDetailsDTO.setUserError("El libro ya existe");

    RestTemplate restTemplate = mock(RestTemplate.class);
    when(restTemplateProvider.getRestTemplate()).thenReturn(restTemplate);

    HttpHeaders headers = new HttpHeaders();

    ResponseEntity<String> value =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(gson.toJson(errorDetailsDTO));

    when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(value);

    BookDTO book = BookFactory.createBook("The House of the Spirits", "Isabel Allende", "Magical Realism");
    book = bookClientService.create(book);
    assertNull(book);

  }
  
  @Test
  void testCreate_ExceptionUnknown() {
    RestTemplate restTemplate = mock(RestTemplate.class);
    when(restTemplateProvider.getRestTemplate()).thenReturn(restTemplate);

    HttpHeaders headers = new HttpHeaders();

    ResponseEntity<String> value =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);

    when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(value);

    BookDTO book = BookFactory.createBook("The House of the Spirits", "Isabel Allende", "Magical Realism");
    book = bookClientService.create(book);
    assertNull(book);

  }

  @Test
  void testGetById() {
    fail("Not yet implemented"); // TODO
  }

  @Test
  void testGetAll() {
    fail("Not yet implemented"); // TODO
  }

  @Test
  void testUpdate() {
    fail("Not yet implemented"); // TODO
  }

  @Test
  void testDelete() {
    fail("Not yet implemented"); // TODO
  }

}
