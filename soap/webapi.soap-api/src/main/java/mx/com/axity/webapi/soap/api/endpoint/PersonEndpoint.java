package mx.com.axity.webapi.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import mx.com.axity.webapi.soap.api.commons.base.PaginatedDTO;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.service.PersonService;
import mx.com.axity.webapi.soap.api.util.person.CreatePersonResponseFactory;
import mx.com.axity.webapi.soap.api.util.person.DeletePersonResponseFactory;
import mx.com.axity.webapi.soap.api.util.person.GetPersonResponseFactory;
import mx.com.axity.webapi.soap.api.util.person.GetPersonsResponseFactory;
import mx.com.axity.webapi.soap.api.util.person.PersonFactory;
import mx.com.axity.webapi.soap.api.util.person.UpdatePersonResponseFactory;
import mx.com.axity.webapi.soap.api.ws.person.CreatePersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.CreatePersonResponse;
import mx.com.axity.webapi.soap.api.ws.person.DeletePersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.DeletePersonResponse;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse;
import mx.com.axity.webapi.soap.api.ws.person.UpdatePersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.UpdatePersonResponse;

/**
 * Endpoint class for Person webservice
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Endpoint
public class PersonEndpoint {
  private static final String NAMESPACE_URI = "http://axity.com.mx/webapi/soap/api/ws/person";

  @Autowired
  private PersonService personService;

  /**
   * Method for search a person by id
   * 
   * @param request
   * @return
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPersonRequest")
  @ResponsePayload
  public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {

    GetPersonResponse response = null;
    try {
      ResponseWrapperDTO<PersonDTO> person = personService.getPerson(request.getId());
      response = GetPersonResponseFactory.transform(person);
    } catch (Exception e) {
      response = GetPersonResponseFactory.transform(e);
    }

    return response;
  }

  /**
   * Method for get a list of person active paginated
   * 
   * @param request
   * @return
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPersonsRequest")
  @ResponsePayload
  public GetPersonsResponse getPersons(@RequestPayload GetPersonsRequest request) {
    GetPersonsResponse response = null;
    try {
      ResponseWrapperDTO<PaginatedDTO<PersonDTO>> paginated =
          personService.getPersons(request.getSize(), request.getOffset());
      response = GetPersonsResponseFactory.transform(paginated);
    } catch (Exception e) {
      response = GetPersonsResponseFactory.transform(e);
    }
    return response;
  }

  /**
   * Method to create a person
   * 
   * @param request
   * @return
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreatePersonRequest")
  @ResponsePayload
  public CreatePersonResponse createPerson(@RequestPayload CreatePersonRequest request) {
    CreatePersonResponse response = null;
    try {
      ResponseWrapperDTO<PersonDTO> person = personService.createPerson(PersonFactory.transform(request.getPerson()));
      response = CreatePersonResponseFactory.transform(person);
    } catch (Exception e) {
      response = CreatePersonResponseFactory.transform(e);
    }
    return response;
  }

  /**
   * Method for update a person data
   * 
   * @param request
   * @return
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdatePersonRequest")
  @ResponsePayload
  public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
    UpdatePersonResponse response = null;
    try {
      ResponseWrapperDTO<PersonDTO> person = personService.updatePerson(PersonFactory.transform(request.getPerson()));
      response = UpdatePersonResponseFactory.transform(person);
    } catch (Exception e) {
      response = UpdatePersonResponseFactory.transform(e);
    }
    return response;
  }

  /***
   * Method for delete a person (inactivate)
   * 
   * @param request
   * @return
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeletePersonRequest")
  @ResponsePayload
  public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) {
    DeletePersonResponse response = new DeletePersonResponse();
    try {
      ResponseWrapperDTO<PersonDTO> person = personService.deletePerson(request.getId());
      response = DeletePersonResponseFactory.transform(person);
    } catch (Exception e) {
      response = DeletePersonResponseFactory.transform(e);
    }
    return response;
  }

}
