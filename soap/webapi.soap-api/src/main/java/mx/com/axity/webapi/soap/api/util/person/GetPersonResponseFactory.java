package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class GetPersonResponseFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO<PersonDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse}
   * 
   * @param response
   * @return
   */
  public static GetPersonResponse transform(ResponseWrapperDTO<PersonDTO> response) {
    GetPersonResponse wrapper = new GetPersonResponse();
    wrapper.setHeader(HeaderFactory.transform(response.getHeader()));
    wrapper.setBody(PersonFactory.transform(response.getBody()));

    return wrapper;
  }

  /**
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.GetPersonResponse}
   * 
   * @param e
   * @return
   */
  public static GetPersonResponse transform(Exception e) {
    GetPersonResponse wrapper = new GetPersonResponse();
    wrapper.setHeader(HeaderFactory.transform(e));
    return wrapper;
  }


}
