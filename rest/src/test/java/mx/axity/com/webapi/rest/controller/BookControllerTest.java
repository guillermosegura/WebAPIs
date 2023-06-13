package mx.axity.com.webapi.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mx.axity.com.webapi.rest.commons.BookDTO;
import mx.axity.com.webapi.rest.commons.PaginatedDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;
import mx.axity.com.webapi.rest.service.BookService;

/**
 * This class contains unit tests for the BookController class.
 * 
 * <p>
 * The tests verify the functionality of the BookController class by mocking the BookService and
 * performing various HTTP requests to the book API endpoints.
 * </p>
 * 
 * <p>
 * The tests cover scenarios such as getting a book by ID, retrieving all books, creating a new
 * book, updating a book, and deleting a book.
 * </p>
 * 
 * <p>
 * The tests use the Spring MVC Test framework along with the MockMvc class to simulate HTTP
 * requests and verify the responses.
 * </p>
 * 
 * <p>
 * Note: The BookService is mocked using the @MockBean annotation from Spring Boot Test. This allows
 * us to specify the expected behavior of the BookService methods and control the responses returned
 * during the tests.
 * </p>
 * 
 * <p>
 * Author: guillermo.segura@axity.com
 * </p>
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ResourceBundleMessageSource messageSource;

  @MockBean
  private BookService bookService;

  /**
   * Unit test for the "getById" endpoint of the BookController class.
   * 
   * <p>
   * This test verifies that the "getById" endpoint returns the expected book when a valid book ID is
   * provided. The BookService is mocked to return a specific book for the given ID.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testGetById() throws Exception {

    String id = "1";
    String title = "The Hitchhiker's Guide to the Galaxy";
    String author = "Douglas Adams";
    String genre = "Science Fiction";
    when(this.bookService.getById(1)).thenReturn(createBook(1, title, author, genre));


    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$.id").value(id)) //
        .andExpect(jsonPath("$.title").value(title)) //
        .andExpect(jsonPath("$.author").value(author)) //
        .andExpect(jsonPath("$.genre").value(genre)) //
        .andReturn();

    assertNotNull(result);
    assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getHeader(HttpHeaders.CONTENT_TYPE));
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
  }

  /**
   * Unit test for the "getById" endpoint of the BookController class when the book is not found.
   * 
   * <p>
   * This test verifies that the "getById" endpoint returns a not found status when the requested book
   * ID does not exist. The BookService is mocked to return null for the given ID.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testGetById_notFound() throws Exception {

    when(this.bookService.getById(99999)).thenReturn(null);


    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/books/99999")) //
        .andExpect(status().isNotFound()) //
        .andReturn();

    assertNotNull(result);
    assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());

  }

  /**
   * Unit test for the "getAll" endpoint of the BookController class.
   * 
   * <p>
   * This test verifies that the "getAll" endpoint returns a paginated list of books with the expected
   * properties. The BookService is mocked to return a specific paginated list of books.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testGetAll() throws Exception {
    BookDTO book1 = createBook(1, "Dune", "Frank Herbert", "Science Fiction");
    BookDTO book2 = createBook(2, "Dune Messiah", "Frank Herbert", "Science Fiction");
    BookDTO book3 = createBook(3, "Children of Dune", "Frank Herbert", "Science Fiction");
    BookDTO book4 = createBook(4, "God Emperor of Dune", "Frank Herbert", "Science Fiction");
    BookDTO book5 = createBook(5, "Heretics of Dune", "Frank Herbert", "Science Fiction");
    BookDTO book6 = createBook(6, "Chapterhouse: Dune", "Frank Herbert", "Science Fiction");

    int size = 10;
    int offset = 0;
    int records = 6;
    int pages = 1;
    List<BookDTO> books = Arrays.asList(book1, book2, book3, book4, book5, book6);
    PaginatedDTO<BookDTO> paginated = new PaginatedDTO<>(books, size, offset, records, pages);


    when(this.bookService.getAll(size, offset)).thenReturn(paginated);


    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/books") //
        .queryParam("size", String.valueOf(size)).queryParam("offset", String.valueOf(offset))) //
        .andExpect(jsonPath("$.size").value(String.valueOf(size))) //
        .andExpect(jsonPath("$.offset").value(String.valueOf(offset))) //
        .andExpect(jsonPath("$.records").value(String.valueOf(records))) //
        .andExpect(jsonPath("$.pages").value(String.valueOf(pages))) //
        .andExpect(jsonPath("$.items").isArray()) //
        .andExpect(jsonPath("$.items[0].id").value(String.valueOf(book1.getId()))) //
        .andExpect(jsonPath("$.items[0].title").value(String.valueOf(book1.getTitle()))) //
        .andExpect(jsonPath("$.items[0].author").value(String.valueOf(book1.getAuthor()))) //
        .andExpect(jsonPath("$.items[0].genre").value(String.valueOf(book1.getGenre()))) //
        .andExpect(status().isOk()) //
        .andReturn();

    assertNotNull(result);
    assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getHeader(HttpHeaders.CONTENT_TYPE));
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

  }

  /**
   * Unit test for the "create" endpoint of the BookController class.
   * 
   * <p>
   * This test verifies that the "create" endpoint successfully creates a new book. The BookService is
   * mocked to return the created book.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testCreate() throws Exception {
    String id = "1";
    String title = "The Hitchhiker's Guide to the Galaxy";
    String author = "Douglas Adams";
    String genre = "Science Fiction";

    BookDTO book = createBook(1, title, author, genre);
    Gson gson = new GsonBuilder().create();

    when(this.bookService.create(any(BookDTO.class))).thenReturn(book);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/books") //
        .content(gson.toJson(book)) //
        .accept(MediaType.APPLICATION_JSON) //
        .contentType(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isCreated()) //
        .andExpect(jsonPath("$.id").value(id)) //
        .andExpect(jsonPath("$.title").value(title)) //
        .andExpect(jsonPath("$.author").value(author)) //
        .andExpect(jsonPath("$.genre").value(genre)) //
        .andReturn();

    assertNotNull(result);
    assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getHeader(HttpHeaders.CONTENT_TYPE));
    assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    assertEquals("/api/books/1", result.getResponse().getHeader(HttpHeaders.LOCATION));
  }

  /**
   * Unit test for the "create" endpoint of the BookController class when an exception occurs.
   * 
   * <p>
   * This test verifies that the "create" endpoint returns an internal server error when an exception
   * is thrown during book creation. The BookService is mocked to throw a BusinessException during the
   * creation process.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testCreate_Exception() throws Exception {
    String title = "The Hitchhiker's Guide to the Galaxy";
    String author = "Douglas Adams";
    String genre = "Science Fiction";
    String userError = this.messageSource.getMessage("error.100", null, Locale.getDefault());

    BookDTO book = createBook(1, title, author, genre);
    Gson gson = new GsonBuilder().create();

    BusinessException be = new BusinessException("Registry found", 100);
    when(this.bookService.create(any(BookDTO.class))).thenThrow(be);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/books") //
        .content(gson.toJson(book)) //
        .accept(MediaType.APPLICATION_JSON) //
        .contentType(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isInternalServerError()) //
        .andExpect(jsonPath("$.errorMessage").value(be.getMessage())) //
        .andExpect(jsonPath("$.errorCode").value(String.valueOf(be.getCode()))) //
        .andExpect(jsonPath("$.userError").value(userError)) //
        .andReturn();

    assertNotNull(result);
    assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getHeader(HttpHeaders.CONTENT_TYPE));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
  }

  /**
   * Unit test for the "update" endpoint of the BookController class.
   * 
   * <p>
   * This test verifies that the "update" endpoint successfully updates an existing book. The
   * BookService is mocked to return the updated book.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testUpdate() throws Exception {
    String id = "1";
    String title = "The Hitchhiker's Guide to the Galaxy";
    String author = "Douglas Adams";
    String genre = "Science Fiction";

    BookDTO book = createBook(1, title, author, genre);
    Gson gson = new GsonBuilder().create();

    when(this.bookService.update(any(BookDTO.class))).thenReturn(book);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1") //
        .content(gson.toJson(book)) //
        .accept(MediaType.APPLICATION_JSON) //
        .contentType(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$.id").value(id)) //
        .andExpect(jsonPath("$.title").value(title)) //
        .andExpect(jsonPath("$.author").value(author)) //
        .andExpect(jsonPath("$.genre").value(genre)) //
        .andReturn();

    assertNotNull(result);
    assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getHeader(HttpHeaders.CONTENT_TYPE));
    assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
  }

  /**
   * Unit test for the "update" endpoint of the BookController class when the book is not found.
   * 
   * <p>
   * This test verifies that the "update" endpoint returns a not found status when the requested book
   * ID for update does not exist. The BookService is mocked to return null for the given ID.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testUpdate_notFound() throws Exception {
    String title = "The Hitchhiker's Guide to the Galaxy";
    String author = "Douglas Adams";
    String genre = "Science Fiction";

    BookDTO book = createBook(1, title, author, genre);
    Gson gson = new GsonBuilder().create();

    when(this.bookService.update(any(BookDTO.class))).thenReturn(null);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1") //
        .content(gson.toJson(book)) //
        .accept(MediaType.APPLICATION_JSON) //
        .contentType(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isNotFound()) //
        .andReturn();

    assertNotNull(result);
    assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
  }


  /**
   * Unit test for the "delete" endpoint of the BookController class.
   * 
   * <p>
   * This test verifies that the "delete" endpoint successfully deletes an existing book. The
   * BookService is mocked to return true when deleting the book.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testDelete() throws Exception {
    when(this.bookService.update(any(BookDTO.class))).thenReturn(null);

    when(this.bookService.delete(1)).thenReturn(true);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1")) //
        .andExpect(status().isNoContent()) //
        .andReturn();

    assertNotNull(result);
    assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
  }

  /**
   * Unit test for the "delete" endpoint of the BookController class when the book is not found.
   * 
   * <p>
   * This test verifies that the "delete" endpoint returns a not found status when the requested book
   * ID for deletion does not exist. The BookService is mocked to return false when deleting the book.
   * </p>
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  void testDelete_notFound() throws Exception {
    when(this.bookService.update(any(BookDTO.class))).thenReturn(null);

    when(this.bookService.delete(1)).thenReturn(false);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1")) //
        .andExpect(status().isNotFound()) //
        .andReturn();

    assertNotNull(result);
    assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
  }

  private static BookDTO createBook(int id, String title, String author, String genre) {
    BookDTO book = new BookDTO();
    book.setId(id);
    book.setTitle(title);
    book.setAuthor(author);
    book.setGenre(genre);
    return book;
  }

}
