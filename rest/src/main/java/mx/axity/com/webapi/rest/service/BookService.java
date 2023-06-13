package mx.axity.com.webapi.rest.service;

import mx.axity.com.webapi.rest.commons.BookDTO;
import mx.axity.com.webapi.rest.commons.PaginatedDTO;

/**
 * Interface for Book.
 * 
 * @author guillermo.segura@axity.com
 *
 */
public interface BookService {
  /**
   * Creates a book.
   * 
   * @param book
   * @return
   */
  BookDTO create(BookDTO book);

  /**
   * Get a book by id
   * 
   * @param bookId
   * @return
   */
  BookDTO getById(int bookId);

  /**
   * Get all books paginated
   * 
   * @param size
   * @param offset
   * @return
   */
  PaginatedDTO<BookDTO> getAll(int size, int offset);

  /**
   * Updates a book
   * 
   * @param book
   * @return
   */
  BookDTO update(BookDTO book);

  /**
   * Deletes a book
   * 
   * @param bookId
   * @return
   */

  boolean delete(int bookId);
}
