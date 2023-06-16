package mx.axity.com.webapi.rest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.axity.com.webapi.rest.model.StudentDO;

@Repository
public interface StudentRepository extends JpaRepository<StudentDO, Integer> {
}
