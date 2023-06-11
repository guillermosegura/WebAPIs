package mx.com.axity.webapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.axity.webapi.model.entity.MovementTypeDO;

/**
 * Repository interface for the {@link mx.com.axity.webapi.model.entity.MovementTypeDO} entity.
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface MovementTypeRepository extends JpaRepository<MovementTypeDO, Integer>
{

}
