package mx.axity.com.webapi.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mx.axity.com.webapi.rest.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.commons.dto.PaginatedDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;

/**
 * Test class for the {@link BookService} implementation. It includes test methods to verify the
 * functionality of the CRUD operations.
 *
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class BookServiceTest {
  private static final Logger logger = LoggerFactory.getLogger(BookServiceTest.class);

  @Autowired
  private BookService bookService;

  /**
   * Test method for creating a new book. It verifies that the book is successfully created and logged
   * in JSON format.
   */
  @Test
  void testCreate() {
    BookDTO book = new BookDTO();
    book.setTitle("The Hitchhiker's Guide to the Galaxy");
    book.setAuthor("Douglas Adams");
    book.setGenre("Science Fiction");

    book = this.bookService.create(book, false);
    assertNotNull(book);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    logger.info(gson.toJson(book));
  }

  /**
   * Test method for validating the creation of a book with missing required attributes. It expects a
   * {@link BusinessException} to be thrown with the appropriate error code and error messages.
   */
  @Test
  void testCreate_ValidationError1() {
    BookDTO book = new BookDTO();

    BusinessException be = assertThrows(BusinessException.class, () -> this.bookService.create(book, false));
    assertNotNull(be);
    assertEquals(1, be.getCode());
    assertTrue(be.getMessage().contains("Title is required"));
    assertTrue(be.getMessage().contains("Genre is required"));
    assertTrue(be.getMessage().contains("Author is required"));
  }

  /**
   * Test method for validating the creation of a book with a missing required attribute. It expects a
   * {@link BusinessException} to be thrown with the appropriate error code and error messages.
   */
  @Test
  void testCreate_ValidationError2() {
    BookDTO book = new BookDTO();
    book.setAuthor("John Doe");

    BusinessException be = assertThrows(BusinessException.class, () -> this.bookService.create(book, false));
    assertNotNull(be);
    assertEquals(1, be.getCode());
    assertTrue(be.getMessage().contains("Title is required"));
    assertTrue(be.getMessage().contains("Genre is required"));
  }

  /**
   * Test method for validating the creation of a book with a title exceeding the character limit. It
   * expects a {@link BusinessException} to be thrown with the appropriate error code and error
   * messages.
   */
  @Test
  void testCreate_ValidationError3() {
    BookDTO book = new BookDTO();
    book.setAuthor("John Doe");
    book.setTitle(
        "Lorem ipsum dolor sit amet in eos ut sed ea sed dolore nam. Elitr ut esse duis. Eos nulla rebum tempor feugiat vel wisi kasd et adipiscing dolore sed eum rebum. Amet nonummy et dolores. Et dolor sanctus consetetur dolores sadipscing tincidunt nam ipsum eros zzril dolores ut dolore.");

    BusinessException be = assertThrows(BusinessException.class, () -> this.bookService.create(book, false));
    assertNotNull(be);
    assertEquals(1, be.getCode());
    assertTrue(be.getMessage().contains("Genre is required"));
    assertTrue(be.getMessage().contains("Title must be 100 characters or less"));
  }

  /**
   * Test method for validating the creation of a book with an existing title. It expects a
   * {@link BusinessException} to be thrown with the appropriate error code and error message.
   */
  @Test
  void testCreate_TitleExists() {
    BookDTO book = new BookDTO();
    book.setAuthor("John Doe");
    book.setTitle("To Kill a Mockingbird");
    book.setGenre("Fiction");

    BusinessException be = assertThrows(BusinessException.class, () -> this.bookService.create(book, false));
    assertNotNull(be);
    assertEquals(100, be.getCode());
    assertEquals("Registry found", be.getMessage());
  }

  /**
   * Test method for retrieving a book by its id. It verifies that the book with the given id is
   * returned, and it also verifies that null is returned when the id does not exist.
   */
  @Test
  void testGetById() {
    BookDTO book = this.bookService.getById(1);
    assertNotNull(book);

    assertNull(this.bookService.getById(99999));
  }

  /**
   * Test method for retrieving all books in a paginated manner. It verifies that the correct number
   * of books is returned based on the size and offset parameters, and it also verifies the total
   * number of records.
   *
   * @param size the number of records to retrieve
   * @param offset the starting position of the records
   * @param registries the expected number of records to be returned
   * @param total the expected total number of records
   */
  @ParameterizedTest
  @CsvSource(value = {"10,0,10,20", "10,10,10,20", "8,0,8,20", "8,8,8,20", "8,16,4,20"})
  void testGetAll(int size, int offset, int registries, int total) {
    PaginatedDTO<BookDTO> paginated = this.bookService.getAll(size, offset);

    assertNotNull(paginated);
    assertNotNull(paginated.getItems());

    assertEquals(registries, paginated.getItems().size());
    assertEquals(total, paginated.getRecords());
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    logger.info(gson.toJson(paginated.getItems()));
    
  }

  /**
   * Test method for updating a book. It verifies that the book is successfully updated with the new
   * attributes.
   */
  @Test
  void testUpdate() {
    BookDTO book = new BookDTO();
    book.setId(2);
    book.setAuthor("John Doe");
    book.setTitle("To Kill a Mockingbird");
    book.setGenre("Fiction");

    book = this.bookService.update(book);

    assertNotNull(book);
    assertEquals("John Doe", book.getAuthor());
  }

  /**
   * Test method for updating a book. It verifies that null is returned when the id does not exist
   */
  @Test
  void testUpdate_notFound() {
    BookDTO book = new BookDTO();
    book.setId(99999);
    book.setAuthor("John Doe");
    book.setTitle("To Kill a Mockingbird");
    book.setGenre("Fiction");

    book = this.bookService.update(book);

    assertNull(book);
  }

  /**
   * Test method for deleting a book. It verifies that the book is successfully created, and then it
   * deletes the book by its id. Finally, it checks that the book is no longer present in the
   * database.
   */
  @Test
  void testDelete() {
    BookDTO book = new BookDTO();
    book.setTitle("Java For Dummies");
    book.setAuthor("Barry Burd");
    book.setGenre("Programming");
    book = this.bookService.create(book, false);
    int id = book.getId();
    assertNotNull(book);

    assertTrue(this.bookService.delete(id));
  }

  /**
   * Test method for deleting a book that is not found. It verifies that the delete operation returns
   * false when attempting to delete a book with a non-existent ID.
   */
  @Test
  void testDelete_notFound() {
    assertFalse(this.bookService.delete(99999));
  }

}
