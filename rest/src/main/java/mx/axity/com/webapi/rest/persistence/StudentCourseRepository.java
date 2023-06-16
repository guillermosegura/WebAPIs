package mx.axity.com.webapi.rest.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import mx.axity.com.webapi.rest.model.StudentCourseDO;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseDO, Integer> {
  
  @Query("SELECT sc FROM StudentCourseDO sc WHERE sc.student.id = :studentId")
  List<StudentCourseDO> findByStudentId(Integer studentId);
  
  @Query("SELECT sc FROM StudentCourseDO sc WHERE sc.course.id = :courseId")
  List<StudentCourseDO> findByCourseId(Integer courseId);
}
