package mx.axity.com.webapi.rest.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.axity.com.webapi.rest.model.BookDO;

/**
 * Repository interface for managing books. This interface extends the JpaRepository interface,
 * providing basic CRUD operations for the BookDO entity.
 *
 * <p>
 * The BookRepository interface allows querying and manipulating BookDO entities in the underlying
 * data store.
 *
 * <p>
 * <b>Note:</b> This interface is annotated with @Repository, indicating that it is a Spring Data
 * repository.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see mx.axity.example.domain.BookDO
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookDO, Integer> {
  /**
   * Retrieves a book by its title.
   *
   * @param title the title of the book to retrieve
   * @return an Optional containing the book if found, or an empty Optional if not found
   */
  Optional<BookDO> getByTitle(String title);
}
