package mx.com.axity.webapi.commons.enums;

/***
 * Enum for the movement types of the table TC_Movement.
 * 
 * @author guillermo.segura@axity.com
 */
public enum MovementType
{
  ADD_BALANCE(1), BALANCE_WITHDRAWAL(2);

  private final int id;

  MovementType( int id )
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public static MovementType fromId( int id )
  {
    for( MovementType type : MovementType.values() )
    {
      if( type.getId() == id )
      {
        return type;
      }
    }
    return null;
  }
}
