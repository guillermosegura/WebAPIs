package mx.com.axity.webapi.soap.api.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.com.axity.webapi.soap.api.model.entity.AccountDO;

/**
 * Repository interface for the {@link mx.com.axity.webapi.soap.api.model.entity.AccountDO} entity.
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountDO, Integer>
{

  /**
   * Method to find the accounts for a person id.
   * 
   * @param personId
   * @return
   */
  @Query("SELECT a FROM AccountDO a WHERE a.person.id = :personId")
  List<AccountDO> findByPersonId( @Param("personId") int personId );

}
