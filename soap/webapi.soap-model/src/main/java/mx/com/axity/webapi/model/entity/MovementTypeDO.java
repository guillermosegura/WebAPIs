package mx.com.axity.webapi.model.entity;

import mx.com.axity.webapi.model.base.AbstractBaseDO;

import java.util.Objects;

import javax.persistence.*;

/***
 * Entity class for table TC_MovementType.
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TC_MovementType")
public class MovementTypeDO extends AbstractBaseDO
{
  private static final long serialVersionUID = -2738937207872487683L;

  @Id
  @Column(name = "cd_id")
  private Integer id;

  @Column(name = "nb_name", nullable = false)
  private String name;

  /**
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId( Integer id )
  {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName( String name )
  {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      MovementTypeDO that = (MovementTypeDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
