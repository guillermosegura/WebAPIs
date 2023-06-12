package mx.axity.com.webapi.soap.client.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsRequest;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse;

/**
 * Implementation class for the Account webservice client.
 * 
 * @author guillermo.segura@axity.com
 *
 */
public class PersonClient extends WebServiceGatewaySupport {

  /**
   * Method to get the person by id
   * 
   * @param request
   * @return
   */
  public GetPersonResponse getPerson(GetPersonRequest request) {
    GetPersonResponse response = (GetPersonResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return response;
  }

  /**
   * Method to get the persons list
   * 
   * @param request
   * @return
   */
  public GetPersonsResponse getPersons(GetPersonsRequest request) {
    GetPersonsResponse response = (GetPersonsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return response;
  }
}
