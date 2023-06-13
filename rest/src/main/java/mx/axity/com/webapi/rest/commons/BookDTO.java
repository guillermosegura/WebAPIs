package mx.axity.com.webapi.rest.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO class for book.
 * 
 * @author guillermo.segura@axity.com
 *
 */
public class BookDTO {

  @Schema(description = "Book ID")
  private Integer id;

  @Schema(description = "Book title")
  private String title;

  @Schema(description = "Book author")
  private String author;

  @Schema(description = "Book genre")
  private String genre;

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @return the genre
   */
  public String getGenre() {
    return genre;
  }

  /**
   * @param genre the genre to set
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }


}
