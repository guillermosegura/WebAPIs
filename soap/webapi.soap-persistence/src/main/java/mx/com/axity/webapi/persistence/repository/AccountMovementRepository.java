package mx.com.axity.webapi.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mx.com.axity.webapi.model.entity.AccountMovementDO;

/**
 * Repository interface for the {@link mx.com.axity.webapi.model.entity.AccountMovementDO} entity.
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface AccountMovementRepository extends PagingAndSortingRepository<AccountMovementDO, Integer>
{
  /**
   * Method to get the last 5 movements.
   * @return
   */
  @Query("SELECT m FROM AccountMovementDO m ORDER BY m.movementTimestamp DESC")
  List<AccountMovementDO> findLast5Movements();
}
