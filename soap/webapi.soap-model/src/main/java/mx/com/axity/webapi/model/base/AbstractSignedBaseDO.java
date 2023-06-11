package mx.com.axity.webapi.model.base;

import javax.persistence.Column;

/***
 * Abstract signed base class for the entities.
 * 
 * @author guillermo.segura@axity.com
 */
public abstract class AbstractSignedBaseDO extends AbstractBaseDO
{
  private static final long serialVersionUID = 4096066246749799988L;

  @Column(name = "st_active", nullable = false)
  protected boolean active;

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract boolean equals( Object object );

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract int hashCode();

  /**
   * @return the active
   */
  public boolean isActive()
  {
    return active;
  }

  /**
   * @param active the active to set
   */
  public void setActive( boolean active )
  {
    this.active = active;
  }

}
