package mx.axity.com.webapi.rest.service.util;

import mx.axity.com.webapi.rest.commons.dto.CourseDTO;
import mx.axity.com.webapi.rest.model.CourseDO;
import mx.axity.com.webapi.rest.model.StudentCourseDO;

public final class CourseFactory {
  public static CourseDTO transform(CourseDO courseDO) {
    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setId(courseDO.getId());
    courseDTO.setName(courseDO.getName());
    courseDTO.setInstructorId(courseDO.getInstructor().getId());
    courseDTO.setInstructor(ProfessorFactory.transform(courseDO.getInstructor()));
    return courseDTO;
  }

  public static CourseDTO transform(StudentCourseDO studentCourseDO) {
    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setId(studentCourseDO.getCourse().getId());
    courseDTO.setName(studentCourseDO.getCourse().getName());
    courseDTO.setInstructorId(studentCourseDO.getCourse().getInstructor().getId());
    return courseDTO;
  }

  public static CourseDO transform(CourseDTO courseDTO) {
    CourseDO courseDO = new CourseDO();
    courseDO.setId(courseDTO.getId());
    courseDO.setName(courseDTO.getName());
    return courseDO;
  }
}
