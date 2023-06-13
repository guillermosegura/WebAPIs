package mx.axity.com.webapi.rest.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.axity.com.webapi.rest.aspectj.JsonResponseInterceptor;
import mx.axity.com.webapi.rest.commons.BookDTO;
import mx.axity.com.webapi.rest.commons.PaginatedDTO;
import mx.axity.com.webapi.rest.service.BookService;

/**
 * CRUD API for books.
 * 
 * @author guillermo.segura@axity.com
 *
 */
@RestController
@RequestMapping("/api/books")
@Tag(name = "books", description = "Operations related to books")
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
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseInterceptor
  public ResponseEntity<BookDTO> getById(@PathVariable("id") Integer id) {
    BookDTO book = this.bookService.getById(id);

    if (book != null) {
      return ResponseEntity.ok(book);
    }

    return ResponseEntity.notFound().build();
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
  public ResponseEntity<BookDTO> create(@RequestBody BookDTO book) {

    ResponseEntity<BookDTO> response;
    BookDTO created = this.bookService.create(book);
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION, "/api/book/" + book.getId());

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
