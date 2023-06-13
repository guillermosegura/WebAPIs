package mx.axity.com.webapi.rest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.axity.com.webapi.rest.model.BookDO;

/**
 * Interface repository for {@link mx.axity.com.webapi.rest.model.BookDO}
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookDO, Integer> {

}
