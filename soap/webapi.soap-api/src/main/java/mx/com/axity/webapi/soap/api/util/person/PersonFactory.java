package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.Person;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.Person}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class PersonFactory {

  /***
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.dto.PersonDTO} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.Person}
   * 
   * @param dto
   * @return
   */
  public static Person transform(PersonDTO dto) {
    Person person = null;
    if (dto != null) {
      person = new Person();
      person.setId(dto.getId());
      person.setName(dto.getName());
      person.setLastname(dto.getLastname());
      person.setEmail(dto.getEmail());
      person.setActive(dto.isActive());
    }
    return person;
  }

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.ws.person.Person} into a
   * {@link mx.com.axity.webapi.soap.api.commons.dto.PersonDTO}
   * 
   * @param person
   * @return
   */
  public static PersonDTO transform(Person person) {
    PersonDTO dto = null;
    if (person != null) {
      dto = new PersonDTO();
      dto.setId(person.getId());
      dto.setName(person.getName());
      dto.setLastname(person.getLastname());
      dto.setEmail(person.getEmail());
      dto.setActive(person.isActive());
    }
    return dto;
  }
}
