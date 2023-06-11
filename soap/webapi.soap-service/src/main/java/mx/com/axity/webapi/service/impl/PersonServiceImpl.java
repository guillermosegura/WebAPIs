package mx.com.axity.webapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mx.com.axity.webapi.commons.base.HeaderDTO;
import mx.com.axity.webapi.commons.base.PaginationDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;
import mx.com.axity.webapi.commons.exception.BusinessException;
import mx.com.axity.webapi.model.entity.PersonDO;
import mx.com.axity.webapi.persistence.repository.PersonRepository;
import mx.com.axity.webapi.service.PersonService;
import mx.com.axity.webapi.service.util.PersonDTOFactory;

/**
 * Implementation class for the {@link mx.com.axity.webapi.service.PersonService} interface.
 * 
 * @author guillermo.segura@axity.com
 */
@Service
public class PersonServiceImpl implements PersonService
{
  @Autowired
  private PersonRepository personRepository;
  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> createPerson( PersonDTO person )
  {
    PersonDO entity = new PersonDO();
    entity.setName( person.getName() );
    entity.setLastname( person.getLastname() );
    entity.setEmail( person.getEmail() );
    entity.setActive( true );
    entity = personRepository.save( entity );

    person.setId( entity.getId() );

    return new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Record created" ), person );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> getPerson( Integer id )
  {
    PersonDO entity = this.personRepository.findById( id )
        .orElseThrow( () -> new BusinessException( 100, "Record not found" ) );

    return new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Record found" ), PersonDTOFactory.transform( entity ) );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PaginationDTO<PersonDTO>> getPersons( int size, int offset )
  {
    PageRequest pageRequest = PageRequest.of( offset, size );
    Page<PersonDO> page = this.personRepository.findAllByActiveIsTrue( pageRequest );

    List<PersonDTO> items = page.getContent().stream().map( PersonDTOFactory::transform )
        .collect( Collectors.toList() );

    PaginationDTO<PersonDTO> result = new PaginationDTO<PersonDTO>( items, size, offset, page.getTotalElements(),
        page.getTotalPages() );

    return new ResponseWrapperDTO<PaginationDTO<PersonDTO>>( new HeaderDTO( 0, "OK", "Records found" ), result );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> updatePerson( PersonDTO person )
  {
    PersonDO entity = this.personRepository.findById( person.getId() )
        .orElseThrow( () -> new BusinessException( 100, "Record not found" ) );

    entity.setName( person.getName() );
    entity.setLastname( person.getLastname() );
    entity.setEmail( person.getEmail() );
    this.personRepository.save( entity );

    return new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Record updated" ), null );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> deletePerson( Integer id )
  {
    PersonDO entity = this.personRepository.findById( id )
        .orElseThrow( () -> new BusinessException( 100, "Record not found" ) );

    if( !entity.isActive() )
    {
      throw new BusinessException( 101, "Record already deleted" );
    }

    entity.setActive( false );
    entity = this.personRepository.save( entity );

    return new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Record deleted" ), null );
  }

}
