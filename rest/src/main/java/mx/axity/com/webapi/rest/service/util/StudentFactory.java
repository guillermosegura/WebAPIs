package mx.axity.com.webapi.rest.service.util;

import mx.axity.com.webapi.rest.commons.dto.StudentDTO;
import mx.axity.com.webapi.rest.model.StudentCourseDO;
import mx.axity.com.webapi.rest.model.StudentDO;

public final class StudentFactory {
  public static StudentDTO transform(StudentDO studentDO) {
      StudentDTO studentDTO = new StudentDTO();
      studentDTO.setId(studentDO.getId());
      studentDTO.setName(studentDO.getName());
      studentDTO.setLastname(studentDO.getLastname());
      studentDTO.setEmail(studentDO.getEmail());
      studentDTO.setAdvisorId(studentDO.getAdvisor().getId());
      studentDTO.setAdvisor(ProfessorFactory.transform(studentDO.getAdvisor()));
      return studentDTO;
  }
  
  public static StudentDTO transform(StudentCourseDO studentCourseDO) {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setId(studentCourseDO.getStudent().getId());
    studentDTO.setName(studentCourseDO.getStudent().getName());
    studentDTO.setLastname(studentCourseDO.getStudent().getLastname());
    studentDTO.setEmail(studentCourseDO.getStudent().getEmail());
    studentDTO.setAdvisorId(studentCourseDO.getStudent().getAdvisor().getId());
    return studentDTO;
}

  public static StudentDO transform(StudentDTO studentDTO) {
      StudentDO studentDO = new StudentDO();
      studentDO.setId(studentDTO.getId());
      studentDO.setName(studentDTO.getName());
      studentDO.setLastname(studentDTO.getLastname());
      studentDO.setEmail(studentDTO.getEmail());
      return studentDO;
  }
}
