package mx.axity.com.webapi.rest.client.service;

import mx.axity.com.webapi.rest.client.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.client.commons.dto.PaginatedDTO;

public interface BookClientService {

  /**
   * Creates a new book.
   *
   * @param book the book to create
   * @return the created book as a BookDTO
   */
  BookDTO create(BookDTO book);

  /**
   * Retrieves a book by its ID.
   *
   * @param bookId the ID of the book to retrieve
   * @return the retrieved book as a BookDTO, or null if not found
   */
  BookDTO getById(int bookId);

  /**
   * Retrieves all books in a paginated manner.
   *
   * @param size the page size (number of books per page)
   * @param offset the offset (starting position) of the page
   * @return a PaginatedDTO containing the list of books in the specified page
   */
  PaginatedDTO<BookDTO> getAll(int size, int offset);

  /**
   * Updates an existing book.
   *
   * @param book the book to update
   * @return the updated book as a BookDTO
   */
  BookDTO update(BookDTO book);

  /**
   * Deletes a book by its ID.
   *
   * @param bookId the ID of the book to delete
   * @return true if the book was successfully deleted, false otherwise
   */
  boolean delete(int bookId);
}
