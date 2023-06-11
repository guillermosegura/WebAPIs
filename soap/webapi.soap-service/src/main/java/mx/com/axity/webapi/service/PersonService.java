package mx.com.axity.webapi.service;

import mx.com.axity.webapi.commons.base.PaginationDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;

/***
 * Interface for manipulating the person object.
 * 
 * @author guillermo.segura@axity.com
 */
public interface PersonService
{
  /**
   * Method to create person.
   * 
   * @param person
   * @return
   */
  ResponseWrapperDTO<PersonDTO> createPerson( PersonDTO person );

  /**
   * Method to get person by id.
   * 
   * @param id
   * @return
   */
  ResponseWrapperDTO<PersonDTO> getPerson( Integer id );

  /**
   * Method to get all the active people in a paginated way.
   * 
   * @param size
   * @param offset
   * @return
   */
  ResponseWrapperDTO<PaginationDTO<PersonDTO>> getPersons( int size, int offset );

  /**
   * Method to update a person.
   * 
   * @param person
   * @return
   */
  ResponseWrapperDTO<PersonDTO> updatePerson( PersonDTO person );

  /***
   * Method to delete a person.
   * 
   * @param id
   * @return
   */
  ResponseWrapperDTO<PersonDTO> deletePerson( Integer id );
}
