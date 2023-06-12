package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.base.PaginatedDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.PaginatedPerson;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.PaginatedPerson}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class PaginatedPersonFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.PaginatedDTO<PersonDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.PaginatedPerson}
   * 
   * @param paginated
   * @return
   */
  public static PaginatedPerson transform(PaginatedDTO<PersonDTO> paginated) {
    PaginatedPerson paginatedPerson = null;
    if (paginated != null) {
      paginatedPerson = new PaginatedPerson();
      paginatedPerson.setItems(PersonListFactory.transform(paginated.getItems()));
      paginatedPerson.setOffset(paginated.getOffset());
      paginatedPerson.setPages(paginated.getPages());
      paginatedPerson.setRecords(paginated.getRecords());
      paginatedPerson.setSize(paginated.getSize());
    }
    return paginatedPerson;
  }

}
