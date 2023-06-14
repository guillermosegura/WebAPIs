package mx.axity.com.webapi.rest.client.commons.util;

import mx.axity.com.webapi.rest.client.commons.dto.BookDTO;

/**
 * Utility class for creating instances of BookDTO.
 * 
 * This class provides static methods for creating BookDTO objects with different parameters. It
 * simplifies the process of creating BookDTO instances by encapsulating the logic in one place.
 * 
 * Note: The createBook methods allow creating BookDTO instances with or without an ID.
 * 
 * Example usage: BookDTO book = BookFactory.createBook("The House of the Spirits", "Isabel
 * Allende", "Magical Realism");
 * 
 * Example usage with ID: BookDTO book = BookFactory.createBook(1, "The House of the Spirits",
 * "Isabel Allende", "Magical Realism");
 * 
 * It is recommended to use this utility class instead of directly creating BookDTO instances to
 * maintain consistency and readability in the codebase.
 * 
 * Note: This class is marked as final to prevent inheritance as it provides only static methods.
 * 
 * For more information on the BookDTO class and its properties, refer to its Javadoc.
 * 
 * @see BookDTO
 * @author guillermo.segura@axity.com
 */
public final class BookFactory {

  /**
   * Creates a new instance of BookDTO without an ID.
   * 
   * @param title The title of the book.
   * @param author The author of the book.
   * @param genre The genre of the book.
   * @return The created BookDTO instance.
   */
  public static BookDTO createBook(String title, String author, String genre) {
    return createBook(null, title, author, genre);
  }

  /**
   * Creates a new instance of BookDTO with the specified ID.
   * 
   * @param id The ID of the book.
   * @param title The title of the book.
   * @param author The author of the book.
   * @param genre The genre of the book.
   * @return The created BookDTO instance.
   */
  public static BookDTO createBook(Integer id, String title, String author, String genre) {
    BookDTO book = new BookDTO();
    book.setId(id);
    book.setTitle(title);
    book.setAuthor(author);
    book.setGenre(genre);
    return book;
  }
}
