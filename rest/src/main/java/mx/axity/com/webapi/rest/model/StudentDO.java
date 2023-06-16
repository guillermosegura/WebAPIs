package mx.axity.com.webapi.rest.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TC_Student")
public class StudentDO implements Serializable {

  private static final long serialVersionUID = 5062274268522946807L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @Column(name = "nb_name")
  private String name;

  @Column(name = "nb_lastname")
  private String lastname;

  @Column(name = "nb_email")
  private String email;

  @ManyToOne
  @JoinColumn(name = "cd_advisor")
  private ProfessorDO advisor;

  @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
  private List<StudentCourseDO> studentCourses;

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
   * @return the advisor
   */
  public ProfessorDO getAdvisor() {
    return advisor;
  }

  /**
   * @param advisor the advisor to set
   */
  public void setAdvisor(ProfessorDO advisor) {
    this.advisor = advisor;
  }

  /**
   * @return the studentCourses
   */
  public List<StudentCourseDO> getStudentCourses() {
    return studentCourses;
  }

  /**
   * @param studentCourses the studentCourses to set
   */
  public void setStudentCourses(List<StudentCourseDO> studentCourses) {
    this.studentCourses = studentCourses;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    StudentDO that = (StudentDO) object;
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
