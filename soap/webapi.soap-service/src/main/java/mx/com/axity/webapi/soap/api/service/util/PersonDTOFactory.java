package mx.com.axity.webapi.soap.api.service.util;

import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.model.entity.PersonDO;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.commons.dto.PersonDTO}
 * 
 * @author guillermo.segura@axity.com
 */
public final class PersonDTOFactory
{
  /**
   * Method to transform an entity {@link mx.com.axity.webapi.soap.api.model.entity.PersonDO} into a DTO
   * {@link mx.com.axity.webapi.soap.api.commons.dto.PersonDTO}
   * 
   * @param entity
   * @return
   */
  public static PersonDTO transform( PersonDO entity )
  {
    PersonDTO person = new PersonDTO();
    person.setId( entity.getId() );
    person.setName( entity.getName() );
    person.setLastname( entity.getLastname() );
    person.setEmail( entity.getEmail() );
    person.setActive( entity.isActive() );
    return person;
  }
}
