package mx.axity.com.webapi.rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.commons.dto.StudentDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;
import mx.axity.com.webapi.rest.model.StudentCourseDO;
import mx.axity.com.webapi.rest.model.StudentDO;
import mx.axity.com.webapi.rest.persistence.StudentCourseRepository;
import mx.axity.com.webapi.rest.persistence.StudentRepository;
import mx.axity.com.webapi.rest.service.StudentService;
import mx.axity.com.webapi.rest.service.util.CourseFactory;
import mx.axity.com.webapi.rest.service.util.StudentFactory;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StudentCourseRepository studentCourseRepository;


  @Override
  public StudentDTO findById(Integer id) {
    StudentDO studentDO = studentRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Student not found with id: " + id, 200));

    StudentDTO student = StudentFactory.transform(studentDO);
    return student;
  }

  @Override
  public List<StudentDTO> findAll() {
    List<StudentDO> studentDOList = studentRepository.findAll();

    List<StudentDTO> students = studentDOList.stream().map(StudentFactory::transform).collect(Collectors.toList());

    return students;
  }

  @Override
  public List<CourseDTO> findCoursesByStudentId(Integer studentId) {
    List<StudentCourseDO> studentCourseList = this.studentCourseRepository.findByStudentId(studentId);

    List<CourseDTO> courses = studentCourseList.stream().map(CourseFactory::transform).collect(Collectors.toList());
    return courses;
  }
}
