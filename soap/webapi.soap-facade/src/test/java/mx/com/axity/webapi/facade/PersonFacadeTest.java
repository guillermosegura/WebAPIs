package mx.com.axity.webapi.facade;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mx.com.axity.webapi.commons.base.HeaderDTO;
import mx.com.axity.webapi.commons.base.PaginationDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;
import mx.com.axity.webapi.commons.exception.BusinessException;
import mx.com.axity.webapi.facade.impl.PersonFacadeImpl;
import mx.com.axity.webapi.service.PersonService;

class PersonFacadeTest
{

  @Mock
  private PersonService personService;

  @InjectMocks
  private PersonFacade personFacade = new PersonFacadeImpl();

  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks( this );
  }

  @Test
  void testCreatePerson_Success()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );

    ResponseWrapperDTO<PersonDTO> expectedResponse = new ResponseWrapperDTO<>(
        new HeaderDTO( 0, "OK", "Record created" ), personDTO );

    // Mock the personService createPerson method
    Mockito.when( personService.createPerson( personDTO ) ).thenReturn( expectedResponse );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.createPerson( personDTO );

    // Verify the response
    Assertions.assertEquals( expectedResponse, response );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).createPerson( personDTO );
  }

  @Test
  void testCreatePerson_BusinessException()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );

    int errorCode = 100;
    String errorMessage = "Record not found";
    String errorDetails = "Details of the error";

    BusinessException businessException = new BusinessException( errorMessage, errorCode, errorDetails );

    // Mock the personService createPerson method to throw a BusinessException
    Mockito.when( personService.createPerson( personDTO ) ).thenThrow( businessException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.createPerson( personDTO );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorDetails, response.getHeader().getDetails() );
    Assertions.assertNull( response.getBody() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).createPerson( personDTO );
  }

  @Test
  void testCreatePerson_GenericException()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );

    String errorMessage = "Unexpected error";

    RuntimeException runtimeException = new RuntimeException( errorMessage );

    // Mock the personService createPerson method to throw a RuntimeException
    Mockito.when( personService.createPerson( personDTO ) ).thenThrow( runtimeException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.createPerson( personDTO );

    // Verify the response
    Assertions.assertEquals( 1, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorMessage, response.getHeader().getDetails() );
    Assertions.assertNull( response.getBody() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).createPerson( personDTO );
  }

  @Test
  void testGetPerson_Success()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );

    ResponseWrapperDTO<PersonDTO> expectedResponse = new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Record found" ),
        personDTO );

    // Mock the personService createPerson method
    Mockito.when( personService.getPerson( 1 ) ).thenReturn( expectedResponse );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.getPerson( 1 );

    // Verify the response
    Assertions.assertEquals( expectedResponse, response );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).getPerson( 1 );
  }

  @Test
  void testGetPerson_BusinessException()
  {
    // Prepare test data
    int errorCode = 100;
    String errorMessage = "Record not found";
    String errorDetails = "Details of the error";

    BusinessException businessException = new BusinessException( errorMessage, errorCode, errorDetails );

    // Mock the personService createPerson method
    Mockito.when( personService.getPerson( 1 ) ).thenThrow( businessException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.getPerson( 1 );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorDetails, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).getPerson( 1 );
  }

  @Test
  void testGetPerson_GenericException()
  {
    // Prepare test data
    int errorCode = 1;
    String errorMessage = "Unexpected error";

    RuntimeException runtimeException = new RuntimeException( errorMessage );

    // Mock the personService createPerson method
    Mockito.when( personService.getPerson( 1 ) ).thenThrow( runtimeException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.getPerson( 1 );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorMessage, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).getPerson( 1 );
  }

  @Test
  void testGetPersons_Success()
  {
    // Prepare test data
    List<PersonDTO> list = new ArrayList<>();
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );
    list.add( personDTO );

    personDTO = new PersonDTO();
    personDTO.setId( 2 );
    personDTO.setName( "Jane" );
    personDTO.setLastname( "Smith" );
    personDTO.setEmail( "jane.smith@example.com" );
    personDTO.setActive( true );
    list.add( personDTO );

    PaginationDTO<PersonDTO> pagination = new PaginationDTO<>( list, 10, 0, 2, 1 );

    ResponseWrapperDTO<PaginationDTO<PersonDTO>> expectedResponse = new ResponseWrapperDTO<>(
        new HeaderDTO( 0, "OK", "Records found" ), pagination );

    // Mock the personService createPerson method
    Mockito.when( personService.getPersons( 10, 0 ) ).thenReturn( expectedResponse );

    // Call the facade method
    ResponseWrapperDTO<PaginationDTO<PersonDTO>> response = personFacade.getPersons( 10, 0 );

    // Verify the response
    Assertions.assertEquals( expectedResponse, response );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).getPersons( 10, 0 );
  }
  
  @Test
  void testGetPersons_BusinessException()
  {
    // Prepare test data
    int errorCode = 100;
    String errorMessage = "Record not found";
    String errorDetails = "Details of the error";

    BusinessException businessException = new BusinessException( errorMessage, errorCode, errorDetails );


    // Mock the personService createPerson method
    Mockito.when( personService.getPersons( 10, 0 ) ).thenThrow( businessException );

    // Call the facade method
    ResponseWrapperDTO<PaginationDTO<PersonDTO>> response = personFacade.getPersons( 10, 0 );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorDetails, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).getPersons( 10, 0 );
  }
  
  @Test
  void testGetPersons_GenericException()
  {
    // Prepare test data
    int errorCode = 1;
    String errorMessage = "Record not found";

    RuntimeException runtimeException = new RuntimeException( errorMessage );


    // Mock the personService createPerson method
    Mockito.when( personService.getPersons( 10, 0 ) ).thenThrow( runtimeException );

    // Call the facade method
    ResponseWrapperDTO<PaginationDTO<PersonDTO>> response = personFacade.getPersons( 10, 0 );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorMessage, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).getPersons( 10, 0 );
  }
  
  @Test
  void testUpdate_Success()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );

    ResponseWrapperDTO<PersonDTO> expectedResponse = new ResponseWrapperDTO<>(
        new HeaderDTO( 0, "OK", "Record updated" ), null );

    // Mock the personService createPerson method
    Mockito.when( personService.updatePerson( personDTO ) ).thenReturn( expectedResponse );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.updatePerson( personDTO );

    // Verify the response
    Assertions.assertEquals( expectedResponse, response );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).updatePerson( personDTO );
  }
  
  @Test
  void testUpdate_BusinessException()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );
    
    int errorCode = 100;
    String errorMessage = "Record not found";
    String errorDetails = "Details of the error";

    BusinessException businessException = new BusinessException( errorMessage, errorCode, errorDetails );


    // Mock the personService createPerson method
    Mockito.when( personService.updatePerson( personDTO ) ).thenThrow( businessException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.updatePerson( personDTO );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorDetails, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).updatePerson( personDTO );
  }
  
  @Test
  void testUpdate_GenericException()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );
    
    int errorCode = 1;
    String errorMessage = "Record not found";

    RuntimeException runtimeException = new RuntimeException( errorMessage );


    // Mock the personService createPerson method
    Mockito.when( personService.updatePerson( personDTO ) ).thenThrow( runtimeException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.updatePerson( personDTO );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorMessage, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).updatePerson( personDTO );
  }
  
  
  @Test
  void testDelete_Success()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );

    ResponseWrapperDTO<PersonDTO> expectedResponse = new ResponseWrapperDTO<>(
        new HeaderDTO( 0, "OK", "Record deleted" ), null );

    // Mock the personService createPerson method
    Mockito.when( personService.deletePerson( 1 ) ).thenReturn( expectedResponse );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.deletePerson( 1 );

    // Verify the response
    Assertions.assertEquals( expectedResponse, response );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).deletePerson( 1 );
  }
  
  @Test
  void testDelete_BusinessException()
  {
    // Prepare test data   
    int errorCode = 100;
    String errorMessage = "Record not found";
    String errorDetails = "Details of the error";

    BusinessException businessException = new BusinessException( errorMessage, errorCode, errorDetails );


    // Mock the personService createPerson method
    Mockito.when( personService.deletePerson( 1 ) ).thenThrow( businessException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.deletePerson( 1 );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorDetails, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).deletePerson( 1 );
  }
  
  @Test
  void testDelete_GenericException()
  {
    // Prepare test data
    PersonDTO personDTO = new PersonDTO();
    personDTO.setId( 1 );
    personDTO.setName( "John" );
    personDTO.setLastname( "Doe" );
    personDTO.setEmail( "john.doe@example.com" );
    personDTO.setActive( true );
    
    int errorCode = 1;
    String errorMessage = "Record not found";

    RuntimeException runtimeException = new RuntimeException( errorMessage );


    // Mock the personService createPerson method
    Mockito.when( personService.deletePerson( 1 ) ).thenThrow( runtimeException );

    // Call the facade method
    ResponseWrapperDTO<PersonDTO> response = personFacade.deletePerson( 1 );

    // Verify the response
    Assertions.assertEquals( errorCode, response.getHeader().getCode() );
    Assertions.assertEquals( errorMessage, response.getHeader().getMessage() );
    Assertions.assertEquals( errorMessage, response.getHeader().getDetails() );

    // Verify that the personService createPerson method was called with the correct argument
    Mockito.verify( personService, Mockito.times( 1 ) ).deletePerson( 1 );
  }

}
