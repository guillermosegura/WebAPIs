package mx.com.axity.webapi.facade;

import mx.com.axity.webapi.commons.base.PaginationDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;

/**
 * Facede interface for manipulating the person object.
 * 
 * @author guillermo.segura@axity.com
 */
public interface PersonFacade
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
