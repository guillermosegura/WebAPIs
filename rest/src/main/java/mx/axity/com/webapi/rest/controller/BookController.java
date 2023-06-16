package mx.axity.com.webapi.rest.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.axity.com.webapi.rest.aspectj.JsonResponseInterceptor;
import mx.axity.com.webapi.rest.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.commons.dto.PaginatedDTO;
import mx.axity.com.webapi.rest.service.BookService;

/**
 * CRUD API for books.
 * 
 * @author guillermo.segura@axity.com
 *
 */
@RestController
@RequestMapping("/api/books")
@Tag(name = "API Books", description = "Operations related to books")
public class BookController {

  @Autowired
  private BookService bookService;

  /***
   * Method for get a book by id.
   * 
   * @param id
   * @return
   */
  @Operation(summary = "Get a book by ID")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book found"),
      @ApiResponse(responseCode = "404", description = "Book not found")})
  @GetMapping(path = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
  @JsonResponseInterceptor
  public ResponseEntity<BookDTO> getById(@PathVariable("id") Integer id,
      @RequestParam(value = "type", required = false, defaultValue = "json") String type) {
    BookDTO book = this.bookService.getById(id);

    ResponseEntity<BookDTO> response;
    if (book == null) {
      response = ResponseEntity.notFound().build();
    } else {
      response = this.createResponse(book, type);
    }

    return response;
  }

  @GetMapping(path = "/example", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookDTO>> findByExample(BookDTO example) {
    // Es equivalente a usar:
    // @RequestParam(value = "id", required = false ) Integer id,
    // @RequestParam(value = "title", required = false ) String title,
    // @RequestParam(value = "author", required = false ) String author,
    // @RequestParam(value = "genre", required = false ) String genre
    // BookDTO example = BookFactory.createBookDTO(id, title, author, genre);

    List<BookDTO> books = this.bookService.findByExample(example);
    if (books.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(books);
  }

  private ResponseEntity<BookDTO> createResponse(BookDTO book, String type) {

    HttpHeaders headers = new HttpHeaders();


    ResponseEntity<BookDTO> response;
    String contentType;
    switch (type.toLowerCase()) {
      case "json":
        contentType = MediaType.APPLICATION_JSON_VALUE;
        break;
      case "xml":
        contentType = MediaType.APPLICATION_XML_VALUE;
        break;
      case "text":
        contentType = MediaType.TEXT_PLAIN_VALUE;
        break;
      default:
        contentType = MediaType.APPLICATION_JSON_VALUE;
    }
    headers.add(HttpHeaders.CONTENT_TYPE, contentType);

    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(book);
  }

  /**
   * Method for get the books paginated.
   * 
   * @param size
   * @param offset
   * @return
   */
  @Operation(summary = "Get all books")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseInterceptor
  public ResponseEntity<PaginatedDTO<BookDTO>> getAll(
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {

    PaginatedDTO<BookDTO> paginated = this.bookService.getAll(size, offset);

    if (paginated.getItems().isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(paginated);
  }

  /**
   * Create a book.
   * 
   * @param book
   * @return
   */
  @Operation(summary = "Create a book")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Book created"),
      @ApiResponse(responseCode = "500", description = "Internal server error")})
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseInterceptor
  public ResponseEntity<BookDTO> create(@RequestBody BookDTO book,
      @RequestHeader(name = "X-AllowDuplicate", required = false, defaultValue = "false") String allowDuplicate) {

    ResponseEntity<BookDTO> response;
    BookDTO created = this.bookService.create(book, Boolean.parseBoolean(allowDuplicate));
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION, "/api/books/" + created.getId());
    headers.add("X-Value", UUID.randomUUID().toString());

    response = ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(created);
    return response;
  }

  /**
   * Updates a book by id.
   * 
   * @param id
   * @param book
   * @return
   */
  @Operation(summary = "Update a book by ID")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book updated"),
      @ApiResponse(responseCode = "404", description = "Book not found")})
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseInterceptor
  public ResponseEntity<BookDTO> update(@PathVariable("id") Integer id, @RequestBody BookDTO book) {

    book.setId(id);
    BookDTO updated = this.bookService.update(book);

    ResponseEntity<BookDTO> response;
    if (updated != null) {
      response = ResponseEntity.ok(book);
    } else {
      response = ResponseEntity.notFound().build();
    }

    return response;
  }

  /**
   * Deletes a book by id.
   * 
   * @param id
   * @return
   */
  @Operation(summary = "Delete a book by ID")
  @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Book deleted"),
      @ApiResponse(responseCode = "404", description = "Book not found")})
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseInterceptor
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    boolean removed = this.bookService.delete(id);

    ResponseEntity<Void> response;
    if (removed) {
      response = ResponseEntity.noContent().build();
    } else {
      response = ResponseEntity.notFound().build();
    }

    return response;
  }

}
