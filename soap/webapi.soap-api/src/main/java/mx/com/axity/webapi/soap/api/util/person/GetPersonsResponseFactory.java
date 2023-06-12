package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.base.PaginatedDTO;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class GetPersonsResponseFactory {

  /**
   * Transforms a
   * {@link mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO<PaginatedDTO<PersonDTO>>}
   * into a {@link mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse}
   * 
   * @param response
   * @return
   */
  public static GetPersonsResponse transform(ResponseWrapperDTO<PaginatedDTO<PersonDTO>> paginated) {
    GetPersonsResponse response = null;
    if (paginated != null) {
      response = new GetPersonsResponse();
      response.setHeader(HeaderFactory.transform(paginated.getHeader()));
      response.setBody(PaginatedPersonFactory.transform(paginated.getBody()));
    }

    return response;
  }

  /**
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.GetPersonsResponse}
   * 
   * @param e
   * @return
   */
  public static GetPersonsResponse transform(Exception e) {
    GetPersonsResponse response = new GetPersonsResponse();
    response.setHeader(HeaderFactory.transform(e));
    return response;
  }
}
