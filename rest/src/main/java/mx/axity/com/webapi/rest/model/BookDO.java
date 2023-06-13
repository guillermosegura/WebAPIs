package mx.axity.com.webapi.rest.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for table TC_Book.
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Entity
@Table(name = "TC_Book")
public class BookDO implements Serializable {

  private static final long serialVersionUID = 8280675162304525435L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @Column(name = "nb_title")
  private String title;

  @Column(name = "nb_author")
  private String author;

  @Column(name = "nd_genre")
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object object) {
    boolean isEquals = false;
    if (this == object) {
      isEquals = true;
    } else if (object != null && object.getClass().equals(this.getClass())) {
      BookDO that = (BookDO) object;

      isEquals = Objects.equals(this.id, that.id);
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

}
