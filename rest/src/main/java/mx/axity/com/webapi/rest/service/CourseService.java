package mx.axity.com.webapi.rest.service;

import java.util.List;
import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.commons.dto.StudentDTO;

public interface CourseService {
  CourseDTO findById(Integer id);

  List<CourseDTO> findAll();

  List<StudentDTO> findStudentsByCourseId(Integer courseId);
}
