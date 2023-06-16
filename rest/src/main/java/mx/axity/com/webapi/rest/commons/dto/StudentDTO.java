package mx.axity.com.webapi.rest.commons.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.Link;

public class StudentDTO {
  private Integer id;
  private String name;
  private String lastname;
  private String email;
  private Integer advisorId;
  private ProfessorDTO advisor;
  private List<CourseDTO> courses;

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
   * @return the lastname
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * @param lastname the lastname to set
   */
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the advisorId
   */
  public Integer getAdvisorId() {
    return advisorId;
  }

  /**
   * @param advisorId the advisorId to set
   */
  public void setAdvisorId(Integer advisorId) {
    this.advisorId = advisorId;
  }

  /**
   * @return the advisor
   */
  public ProfessorDTO getAdvisor() {
    return advisor;
  }

  /**
   * @param advisor the advisor to set
   */
  public void setAdvisor(ProfessorDTO advisor) {
    this.advisor = advisor;
  }

  /**
   * @return the courses
   */
  public List<CourseDTO> getCourses() {
    return courses;
  }

  /**
   * @param courses the courses to set
   */
  public void setCourses(List<CourseDTO> courses) {
    this.courses = courses;
  }
}
