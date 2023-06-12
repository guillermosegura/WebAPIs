package mx.com.axity.webapi.soap.api.util.person;

import java.util.List;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.PersonList;

/***
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.PersonList}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class PersonListFactory {

  /***
   * Transforms a {@link java.util.List<PersonDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.PersonList}
   * 
   * @param items
   * @return
   */
  public static PersonList transform(List<PersonDTO> items) {
    final PersonList personList = new PersonList();
    if (items != null) {
      items.stream().forEach(i -> personList.getPerson().add(PersonFactory.transform(i)));
    }
    return personList;
  }

}
