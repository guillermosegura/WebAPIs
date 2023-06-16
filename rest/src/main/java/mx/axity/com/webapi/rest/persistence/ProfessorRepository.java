package mx.axity.com.webapi.rest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.axity.com.webapi.rest.model.ProfessorDO;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorDO, Integer> {
}
