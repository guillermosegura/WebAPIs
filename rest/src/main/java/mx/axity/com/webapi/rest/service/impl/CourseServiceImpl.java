package mx.axity.com.webapi.rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.commons.dto.StudentDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;
import mx.axity.com.webapi.rest.model.CourseDO;
import mx.axity.com.webapi.rest.model.StudentCourseDO;
import mx.axity.com.webapi.rest.persistence.CourseRepository;
import mx.axity.com.webapi.rest.persistence.StudentCourseRepository;
import mx.axity.com.webapi.rest.service.CourseService;
import mx.axity.com.webapi.rest.service.util.CourseFactory;
import mx.axity.com.webapi.rest.service.util.StudentFactory;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StudentCourseRepository studentCourseRepository;

  @Override
  public CourseDTO findById(Integer id) {
    CourseDO courseDO =
        courseRepository.findById(id).orElseThrow(() -> new BusinessException("Course not found with id: " + id, 200));
    return CourseFactory.transform(courseDO);
  }

  @Override
  public List<CourseDTO> findAll() {
    List<CourseDO> courseDOList = courseRepository.findAll();
    List<CourseDTO> courses = courseDOList.stream().map(CourseFactory::transform).collect(Collectors.toList());
    
    return courses;
  }

  @Override
  public  List<StudentDTO> findStudentsByCourseId(Integer courseId) {
    List<StudentCourseDO> studentCourseList = this.studentCourseRepository.findByCourseId(courseId);

    List<StudentDTO> students = studentCourseList.stream().map(StudentFactory::transform).collect(Collectors.toList());
    return students;
  }
}
