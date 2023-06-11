package mx.com.axity.webapi.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mx.com.axity.webapi.model.entity.PersonDO;

/**
 * Repository interface for the {@link mx.com.axity.webapi.model.entity.PersonDO} entity.
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonDO, Integer>
{
  /**
   * Method to search the records of active people in a paginated way.
   * 
   * @param pageable the pageable request.
   * @return
   */
  Page<PersonDO> findAllByActiveIsTrue( Pageable pageable );
}
