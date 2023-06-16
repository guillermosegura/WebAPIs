package mx.axity.com.webapi.rest.service;

import java.util.List;
import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.commons.dto.StudentDTO;

public interface StudentService {
  StudentDTO findById(Integer id);

  List<StudentDTO> findAll();

  List<CourseDTO> findCoursesByStudentId(Integer studentId);
}
