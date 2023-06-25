package mx.axity.com.webapi.rest.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
  private Integer id;
  private String name;
  private ProfessorDTO instructor;
  private Integer instructorId;

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the instructor
   */
  public ProfessorDTO getInstructor() {
    return instructor;
  }

  /**
   * @param instructor the instructor to set
   */
  public void setInstructor(ProfessorDTO instructor) {
    this.instructor = instructor;
  }

  /**
   * @return the instructorId
   */
  public Integer getInstructorId() {
    return instructorId;
  }

  /**
   * @param instructorId the instructorId to set
   */
  public void setInstructorId(Integer instructorId) {
    this.instructorId = instructorId;
  }

}
