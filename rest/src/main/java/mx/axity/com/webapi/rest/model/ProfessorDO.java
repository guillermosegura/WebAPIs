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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TC_Professor")
public class ProfessorDO implements Serializable {

  private static final long serialVersionUID = -6369390632910478766L;

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

  @OneToMany(mappedBy = "advisor", fetch = FetchType.LAZY)
  private List<StudentDO> students;

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
   * @return the students
   */
  public List<StudentDO> getStudents() {
    return students;
  }

  /**
   * @param students the students to set
   */
  public void setStudents(List<StudentDO> students) {
    this.students = students;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ProfessorDO that = (ProfessorDO) object;
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
