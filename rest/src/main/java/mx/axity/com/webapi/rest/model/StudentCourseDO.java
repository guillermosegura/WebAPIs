package mx.axity.com.webapi.rest.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TR_StudentCourse")
public class StudentCourseDO implements Serializable {

  private static final long serialVersionUID = -6147184999059370294L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cd_student")
  private StudentDO student;

  @ManyToOne
  @JoinColumn(name = "cd_course")
  private CourseDO course;

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
   * @return the student
   */
  public StudentDO getStudent() {
    return student;
  }

  /**
   * @param student the student to set
   */
  public void setStudent(StudentDO student) {
    this.student = student;
  }

  /**
   * @return the course
   */
  public CourseDO getCourse() {
    return course;
  }

  /**
   * @param course the course to set
   */
  public void setCourse(CourseDO course) {
    this.course = course;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    StudentCourseDO that = (StudentCourseDO) object;
    return Objects.equals(id, that.id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

}
