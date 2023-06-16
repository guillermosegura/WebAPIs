package mx.axity.com.webapi.rest.client.service.impl;

import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mx.axity.com.webapi.rest.client.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.client.commons.dto.ErrorDetailsDTO;
import mx.axity.com.webapi.rest.client.commons.dto.PaginatedDTO;
import mx.axity.com.webapi.rest.client.service.BookClientService;
import mx.axity.com.webapi.rest.client.service.RestTemplateProvider;

@Service
public class BookClientServiceImpl implements BookClientService {
  private static final Logger logger = LoggerFactory.getLogger(BookClientServiceImpl.class);

  @Value(value = "${services.book.apiUrl}")
  private String baseApiUrl;

  @Autowired
  private RestTemplateProvider restTemplateProvider;


  @Override
  public BookDTO create(BookDTO book) {
    RestTemplate restTemplate = restTemplateProvider.getRestTemplate();

    // Set the request headers (if needed)
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create the request entity with the BookDTO payload and headers
    HttpEntity<BookDTO> requestEntity = new HttpEntity<>(book, headers);

    // Make the POST request
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseApiUrl, requestEntity, String.class);

    // Retrieve the response
    HttpStatusCode statusCode = responseEntity.getStatusCode();
    BookDTO responseBody = null;

    // Handle the response
    Gson gson = new GsonBuilder().create();
    if (statusCode == HttpStatus.CREATED) {
      String body = responseEntity.getBody();

      responseBody = gson.fromJson(body, BookDTO.class);
      logger.info("Book created successfully. ID: {}", responseBody.getId());
    } else {
      String errorBody = responseEntity.getBody() != null ? responseEntity.getBody().toString() : "{}";
      ErrorDetailsDTO details = gson.fromJson(errorBody, ErrorDetailsDTO.class);
      logger.warn("Failed to retrieve book. Status code: {}. Error code: {}, ErrorMessage: {}, UserError: {}",
          statusCode, details.getErrorCode(), details.getErrorMessage(), details.getUserError());
    }

    return responseBody;
  }

  @Override
  public BookDTO getById(int bookId) {
    RestTemplate restTemplate = restTemplateProvider.getRestTemplate();

    String url = UriComponentsBuilder.fromHttpUrl(baseApiUrl) //
        .path("/{id}") //
        .buildAndExpand(bookId).toUriString();

    // Make the GET request
    ResponseEntity<BookDTO> responseEntity = restTemplate.getForEntity(url, BookDTO.class);

    // Retrieve the response
    HttpStatusCode statusCode = responseEntity.getStatusCode();
    BookDTO responseBody = null;

    // Handle the response
    if (statusCode == HttpStatus.OK) {
      responseBody = responseEntity.getBody();
      logger.info("Book found, ID: {}", responseBody.getId());
    } else {
      logger.warn("Failed to find the book. Status code: {}", statusCode);
    }

    return responseBody;
  }

  @Override
  public PaginatedDTO<BookDTO> getAll(int size, int offset) {
    RestTemplate restTemplate = restTemplateProvider.getRestTemplate();

    String url = UriComponentsBuilder.fromHttpUrl(baseApiUrl) //
        .queryParam("size", size) //
        .queryParam("offset", offset).toUriString();

    // Make the GET request
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

    // Retrieve the response
    HttpStatusCode statusCode = responseEntity.getStatusCode();
    PaginatedDTO<BookDTO> paginated = null;

    // Handle the response
    Gson gson = new GsonBuilder().create();
    if (statusCode == HttpStatus.OK) {
      Type paginatedType = new TypeToken<PaginatedDTO<BookDTO>>() {}.getType();
      paginated = gson.fromJson(responseEntity.getBody(), paginatedType);
      logger.info("Books found items: {}, records: {}", paginated.getItems().size(), paginated.getRecords());
    } else {
      logger.warn("Failed to create book. Status code: {}", statusCode);
    }

    return paginated;
  }

  @Override
  public BookDTO update(BookDTO book) {
    RestTemplate restTemplate = restTemplateProvider.getRestTemplate();

    String url = UriComponentsBuilder.fromHttpUrl(baseApiUrl) //
        .path("/{id}") //
        .buildAndExpand(book.getId()).toUriString();

    // Set the request headers (if needed)
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create the request entity with the BookDTO payload and headers
    HttpEntity<BookDTO> requestEntity = new HttpEntity<>(book, headers);

    // Make the PUT request
    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

    // Retrieve the response
    HttpStatusCode statusCode = responseEntity.getStatusCode();
    BookDTO responseBody = null;

    // Handle the response
    Gson gson = new GsonBuilder().create();
    if (statusCode == HttpStatus.OK) {
      String body = responseEntity.getBody();

      responseBody = gson.fromJson(body, BookDTO.class);
      logger.info("Book updated successfully. ID: {}", responseBody.getId());
    } else if (statusCode == HttpStatus.NOT_FOUND) {
      logger.info("Failed to find the book. Status code: {}", statusCode);
    } else {
      String errorBody = responseEntity.getBody() != null ? responseEntity.getBody().toString() : "{}";
      ErrorDetailsDTO details = gson.fromJson(errorBody, ErrorDetailsDTO.class);
      logger.warn("Failed to update book. Status code: {}. Error code: {}, ErrorMessage: {}, UserError: {}", statusCode,
          details.getErrorCode(), details.getErrorMessage(), details.getUserError());
    }

    return responseBody;
  }

  @Override
  public boolean delete(int bookId) {
    RestTemplate restTemplate = restTemplateProvider.getRestTemplate();

    String url = UriComponentsBuilder.fromHttpUrl(baseApiUrl) //
        .path("/{id}") //
        .buildAndExpand(bookId).toUriString();

    // Set the request headers (if needed)
    HttpHeaders headers = new HttpHeaders();

    // Create a DELETE request entity
    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

    // Make the DELETE request
    ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);

    // Retrieve the response
    HttpStatusCode statusCode = responseEntity.getStatusCode();

    // Handle the response
    Gson gson = new GsonBuilder().create();
    boolean deleted = false;
    if (statusCode == HttpStatus.NO_CONTENT) {
      deleted = true;
      logger.info("Book deleted successfully.");
    } else if (statusCode == HttpStatus.NOT_FOUND) {
      logger.info("Failed to find the book. Status code: {}", statusCode);
    } else {
      String errorBody = responseEntity.getBody() != null ? responseEntity.getBody().toString() : "{}";
      ErrorDetailsDTO details = gson.fromJson(errorBody, ErrorDetailsDTO.class);
      logger.warn("Failed to update book. Status code: {}. Error code: {}, ErrorMessage: {}, UserError: {}", statusCode,
          details.getErrorCode(), details.getErrorMessage(), details.getUserError());
    }

    return deleted;
  }

}
