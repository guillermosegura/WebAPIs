package mx.com.axity.webapi.model.base;

import java.io.Serializable;

/**
 * Abstract base class for the entities.
 * 
 * @author guillermo.segura@axity.com
 */
public abstract class AbstractBaseDO implements Serializable
{
  private static final long serialVersionUID = 348877518774872800L;

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

}
