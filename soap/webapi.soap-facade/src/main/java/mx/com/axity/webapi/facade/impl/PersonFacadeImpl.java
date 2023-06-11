package mx.com.axity.webapi.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.axity.webapi.commons.base.HeaderDTO;
import mx.com.axity.webapi.commons.base.PaginationDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;
import mx.com.axity.webapi.commons.exception.BusinessException;
import mx.com.axity.webapi.facade.PersonFacade;
import mx.com.axity.webapi.service.PersonService;

/**
 * Implementation class of the interface {@link mx.com.axity.webapi.facade.PersonFacade}
 * 
 * @author guillermo.segura@axity.com
 */
@Service
public class PersonFacadeImpl implements PersonFacade
{

  @Autowired
  private PersonService personService;

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> createPerson( PersonDTO person )
  {
    ResponseWrapperDTO<PersonDTO> response;
    try
    {
      response = this.personService.createPerson( person );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ), null );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> getPerson( Integer id )
  {
    ResponseWrapperDTO<PersonDTO> response;
    try
    {
      response = this.personService.getPerson( id );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ), null );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PaginationDTO<PersonDTO>> getPersons( int size, int offset )
  {
    ResponseWrapperDTO<PaginationDTO<PersonDTO>> response;
    try
    {
      response = this.personService.getPersons( size, offset );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<PaginationDTO<PersonDTO>>(
          new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ), null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<PaginationDTO<PersonDTO>>(
          new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ), null );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> updatePerson( PersonDTO person )
  {
    ResponseWrapperDTO<PersonDTO> response;
    try
    {
      response = this.personService.updatePerson( person );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ), null );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> deletePerson( Integer id )
  {
    ResponseWrapperDTO<PersonDTO> response;
    try
    {
      response = this.personService.deletePerson( id );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<PersonDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ), null );
    }

    return response;
  }

}
