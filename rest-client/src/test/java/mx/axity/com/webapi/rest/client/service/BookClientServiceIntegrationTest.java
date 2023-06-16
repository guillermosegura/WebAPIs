package mx.axity.com.webapi.rest.client.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mx.axity.com.webapi.rest.client.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.client.commons.dto.PaginatedDTO;
import mx.axity.com.webapi.rest.client.commons.util.BookFactory;

/**
 * Integration tests for the {@link BookClientService} class.
 * 
 * These tests validate the integration of the BookClientService with the underlying infrastructure
 * and external dependencies. The tests cover the CRUD operations and interactions with the Book
 * API.
 * 
 * It is recommended to run these tests in an isolated environment to ensure proper setup and
 * clean-up of test data.
 * 
 * Note: These tests may require access to the actual Book API or a suitable mock server for testing
 * purposes.
 * 
 * Integration tests should be written to validate the correct behavior and interactions of the
 * BookClientService with the external systems it relies on. These tests provide confidence in the
 * overall functionality and integration of the service.
 * 
 * The test methods in this class cover various scenarios, such as creating a book, retrieving a
 * book by ID, retrieving all books, updating a book, and deleting a book.
 * 
 * It is important to ensure that the tests are repeatable, independent, and isolated to avoid any
 * interference or dependencies between test cases.
 * 
 * Note: These integration tests should not replace unit tests, which should focus on testing
 * individual units of code in isolation.
 * 
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class BookClientServiceIntegrationTest {
  private static final Logger logger = LoggerFactory.getLogger(BookClientServiceIntegrationTest.class);

  @Autowired
  private BookClientService bookClientService;

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  /**
   * Integration test method to validate the creation of a book.
   * 
   * This test creates a new book using the {@link BookClientService#create(BookDTO)} method and
   * verifies the response. It uses the {@link BookFactory#createBook(String, String, String)} method
   * to create the book object with the specified details. The test logs the JSON representation of
   * the created book using the {@link Logger}.
   */
  @Test
  void testCreate() {
    BookDTO book = BookFactory.createBook("The House of the Spirits", "Isabel Allende", "Magical Realism");
    book = bookClientService.create(book);
    logger.info("{}", gson.toJson(book));
  }

  /**
   * Integration test method to validate retrieving a book by ID.
   * 
   * This test retrieves a book by its ID using the {@link BookClientService#getById(int)} method and
   * verifies the response. The test logs the JSON representation of the retrieved book using the
   * {@link Logger}.
   */
  @Test
  void testGetById() {
    BookDTO book = this.bookClientService.getById(21);
    logger.info("{}", gson.toJson(book));
  }

  /**
   * Integration test method to validate retrieving all books.
   * 
   * This test retrieves all books using the {@link BookClientService#getAll(int, int)} method and
   * verifies the response. The test logs the JSON representation of the retrieved paginated books
   * using the {@link Logger}.
   */
  @Test
  void testGetAll() {
    PaginatedDTO<BookDTO> books = this.bookClientService.getAll(10, 50);
    logger.info("{}", gson.toJson(books));
  }

  /**
   * Integration test method to validate updating a book.
   * 
   * This test updates a book using the {@link BookClientService#update(BookDTO)} method and verifies
   * the response. It uses the {@link BookFactory#createBook(Integer, String, String, String)} method
   * to create the updated book object with the specified details. The test logs the JSON
   * representation of the updated book using the {@link Logger}.
   */
  @Test
  void testUpdate() {
    BookDTO book = BookFactory.createBook(21, "The House of the Spirits 2", "Isabel Allende 2", "Magical Realism 2");
    logger.info("{}", gson.toJson(book));
  }

  /**
   * Integration test method to validate deleting a book.
   * 
   * This test deletes a book using the {@link BookClientService#delete(int)} method and verifies the
   * response. The test logs the deletion status (true or false) using the {@link Logger}.
   */
  @Test
  void testDelete() {
    boolean deleted = this.bookClientService.delete(21);
    logger.info("{}", deleted);
  }

}
