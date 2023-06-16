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
@Table(name = "TC_Course")
public class CourseDO implements Serializable {
  private static final long serialVersionUID = 6252798003138382484L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;

  @Column(name = "nb_name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "cd_instructor")
  private ProfessorDO instructor;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
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
   * @return the instructor
   */
  public ProfessorDO getInstructor() {
    return instructor;
  }

  /**
   * @param instructor the instructor to set
   */
  public void setInstructor(ProfessorDO instructor) {
    this.instructor = instructor;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    CourseDO that = (CourseDO) object;
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
