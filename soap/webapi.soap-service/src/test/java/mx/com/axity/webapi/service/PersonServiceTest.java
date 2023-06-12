package mx.com.axity.webapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import mx.com.axity.webapi.soap.api.commons.base.PaginatedDTO;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.commons.exception.BusinessException;
import mx.com.axity.webapi.soap.api.model.entity.PersonDO;
import mx.com.axity.webapi.soap.api.persistence.repository.PersonRepository;
import mx.com.axity.webapi.soap.api.service.PersonService;
import mx.com.axity.webapi.soap.api.service.impl.PersonServiceImpl;

/***
 * JUnit test class for {@link mx.com.axity.webapi.soap.api.service.PersonService}.
 * 
 * @author guillermo.segura@axity.com
 */
class PersonServiceTest
{
  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService = new PersonServiceImpl();

  @BeforeEach
  void setup()
  {
    MockitoAnnotations.openMocks( this );
  }

  @Test
  void testCreatePerson()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );

    // Mock the repository save method
    PersonDO savedPerson = new PersonDO();
    savedPerson.setId( 1 );
    Mockito.when( personRepository.save( Mockito.any( PersonDO.class ) ) ).thenReturn( savedPerson );

    // Call the service method
    ResponseWrapperDTO<PersonDTO> response = personService.createPerson( personDTO );

    // Verify the result
    Assertions.assertEquals( 0, response.getHeader().getCode() );
    Assertions.assertEquals( "OK", response.getHeader().getMessage() );
    Assertions.assertEquals( "Record created", response.getHeader().getDetails() );
    Assertions.assertEquals( 1, response.getBody().getId() );
    Assertions.assertEquals( "John", response.getBody().getName() );
    Assertions.assertEquals( "Doe", response.getBody().getLastname() );
    Assertions.assertEquals( "john.doe@example.com", response.getBody().getEmail() );

    // Verify that the save method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).save( Mockito.any( PersonDO.class ) );
  }

  @Test
  void testGetPerson_RecordFound()
  {
    // Prepare test data
    int personId = 1;
    PersonDO personDO = new PersonDO();
    personDO.setId( personId );
    personDO.setName( "John" );
    personDO.setLastname( "Doe" );
    personDO.setEmail( "john.doe@example.com" );

    // Mock the repository findById method
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.of( personDO ) );

    // Call the service method
    ResponseWrapperDTO<PersonDTO> response = personService.getPerson( personId );

    // Verify the result
    Assertions.assertEquals( 0, response.getHeader().getCode() );
    Assertions.assertEquals( "OK", response.getHeader().getMessage() );
    Assertions.assertEquals( "Record found", response.getHeader().getDetails() );
    Assertions.assertEquals( personId, response.getBody().getId() );
    Assertions.assertEquals( "John", response.getBody().getName() );
    Assertions.assertEquals( "Doe", response.getBody().getLastname() );
    Assertions.assertEquals( "john.doe@example.com", response.getBody().getEmail() );

    // Verify that the findById method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
  }

  @Test
  void testGetPerson_RecordNotFound()
  {
    // Prepare test data
    int personId = 1;

    // Mock the repository findById method to return Optional.empty()
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.empty() );

    // Call the service method and assert that it throws a BusinessException
    BusinessException exception = Assertions.assertThrows( BusinessException.class,
      () -> personService.getPerson( personId ) );

    // Verify the exception details
    Assertions.assertEquals( 100, exception.getCode() );
    Assertions.assertEquals( "Record not found", exception.getDetails() );

    // Verify that the findById method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
  }

  @Test
  void testGetPersons()
  {
    // Prepare test data
    int size = 2;
    int offset = 0;
    List<PersonDO> personDOList = new ArrayList<>();
    personDOList.add( createPersonDO( 1, "John", "Doe", "john.doe@example.com" ) );
    personDOList.add( createPersonDO( 2, "Jane", "Smith", "jane.smith@example.com" ) );
    Page<PersonDO> page = Mockito.mock( Page.class );
    Mockito.when( page.getContent() ).thenReturn( personDOList );
    Mockito.when( page.getSize() ).thenReturn( size );
    Mockito.when( page.getNumber() ).thenReturn( offset );
    Mockito.when( page.getTotalPages() ).thenReturn( 1 );
    Mockito.when( page.getTotalElements() ).thenReturn( (long) personDOList.size() );

    // Mock the repository findAll method
    Mockito.when( personRepository.findAllByActiveIsTrue( Mockito.any( PageRequest.class ) ) ).thenReturn( page );

    // Call the service method
    ResponseWrapperDTO<PaginatedDTO<PersonDTO>> response = personService.getPersons( size, offset );
    PaginatedDTO<PersonDTO> paginationDTO = response.getBody();

    // Verify the result
    Assertions.assertEquals( 0, response.getHeader().getCode() );
    Assertions.assertEquals( "OK", response.getHeader().getMessage() );
    Assertions.assertEquals( size, paginationDTO.getSize() );
    Assertions.assertEquals( offset, paginationDTO.getOffset() );
    Assertions.assertEquals( personDOList.size(), paginationDTO.getRecords() );
    Assertions.assertEquals( personDOList.size(), paginationDTO.getItems().size() );

    // Verify that the findAllByActiveIsTrue method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findAllByActiveIsTrue( Mockito.any( PageRequest.class ) );
  }

  @Test
  void testUpdatePerson_RecordFound()
  {
    // Prepare test data
    int personId = 1;
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( personId );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );

    PersonDO personDO = new PersonDO();
    personDO.setId( personId );

    // Mock the repository findById and save methods
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.of( personDO ) );
    Mockito.when( personRepository.save( Mockito.any( PersonDO.class ) ) ).thenReturn( personDO );

    // Call the service method
    ResponseWrapperDTO<PersonDTO> response = personService.updatePerson( personDTO );

    // Verify the result
    Assertions.assertEquals( 0, response.getHeader().getCode() );
    Assertions.assertEquals( "OK", response.getHeader().getMessage() );
    Assertions.assertEquals( "Record updated", response.getHeader().getDetails() );
    Assertions.assertNull( response.getBody() );

    // Verify that the findById and save methods were called with the correct arguments
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
    Mockito.verify( personRepository, Mockito.times( 1 ) ).save( Mockito.any( PersonDO.class ) );
  }

  @Test
  void testUpdatePerson_RecordNotFound()
  {
    // Prepare test data
    int personId = 1;
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( personId );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );

    // Mock the repository findById method to return Optional.empty()
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.empty() );

    // Call the service method and assert that it throws a BusinessException
    BusinessException exception = Assertions.assertThrows( BusinessException.class,
      () -> personService.updatePerson( personDTO ) );

    // Verify the exception details
    Assertions.assertEquals( 100, exception.getCode() );
    Assertions.assertEquals( "Record not found", exception.getDetails() );

    // Verify that the findById method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
  }

  @Test
  void testDeletePerson_RecordFound()
  {
    // Prepare test data
    int personId = 1;
    PersonDO personDO = createPersonDO( personId, "John", "Doe", "john.doe@example.com" );

    // Mock the repository findById and save methods
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.of( personDO ) );
    Mockito.when( personRepository.save( Mockito.any( PersonDO.class ) ) ).thenReturn( personDO );

    // Call the service method
    ResponseWrapperDTO<PersonDTO> response = personService.deletePerson( personId );

    // Verify the result
    Assertions.assertEquals( 0, response.getHeader().getCode() );
    Assertions.assertEquals( "OK", response.getHeader().getMessage() );
    Assertions.assertEquals( "Record deleted", response.getHeader().getDetails() );
    Assertions.assertNull( response.getBody() );

    // Verify that the findById and save methods were called with the correct arguments
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
    Mockito.verify( personRepository, Mockito.times( 1 ) ).save( Mockito.any( PersonDO.class ) );
  }

  @Test
  void testDeletePerson_RecordNotFound()
  {
    // Prepare test data
    int personId = 1;

    // Mock the repository findById method to return Optional.empty()
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.empty() );

    // Call the service method and assert that it throws a BusinessException
    BusinessException exception = Assertions.assertThrows( BusinessException.class,
      () -> personService.deletePerson( personId ) );

    // Verify the exception details
    Assertions.assertEquals( 100, exception.getCode() );
    Assertions.assertEquals( "Record not found", exception.getDetails() );

    // Verify that the findById method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
  }

  @Test
  void testDeletePerson_RecordAlreadyDeleted()
  {
    // Prepare test data
    int personId = 1;
    PersonDO personDO = createPersonDO( personId, "John", "Doe", "john.doe@example.com", false );

    // Mock the repository findById method to return Optional.empty()
    Mockito.when( personRepository.findById( personId ) ).thenReturn( Optional.of( personDO ) );

    // Call the service method and assert that it throws a BusinessException
    BusinessException exception = Assertions.assertThrows( BusinessException.class,
      () -> personService.deletePerson( personId ) );

    // Verify the exception details
    Assertions.assertEquals( 101, exception.getCode() );
    Assertions.assertEquals( "Record already deleted", exception.getDetails() );

    // Verify that the findById method was called with the correct argument
    Mockito.verify( personRepository, Mockito.times( 1 ) ).findById( personId );
  }

  private PersonDO createPersonDO( int id, String name, String lastname, String email )
  {
    return this.createPersonDO( id, name, lastname, email, true );
  }

  private PersonDO createPersonDO( int id, String name, String lastname, String email, boolean active )
  {
    PersonDO personDO = new PersonDO();
    personDO.setId( id );
    personDO.setName( name );
    personDO.setLastname( lastname );
    personDO.setEmail( email );
    personDO.setActive( active );
    return personDO;
  }
}
