package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.CreatePersonResponse;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.CreatePersonResponse}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class CreatePersonResponseFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO<PersonDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.CreatePersonResponse}
   * 
   * @param response
   * @return
   */
  public static CreatePersonResponse transform(ResponseWrapperDTO<PersonDTO> response) {
    CreatePersonResponse wrapper = new CreatePersonResponse();
    wrapper.setHeader(HeaderFactory.transform(response.getHeader()));
    wrapper.setBody(PersonFactory.transform(response.getBody()));

    return wrapper;
  }

  /**
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.CreatePersonResponse}
   * 
   * @param e
   * @return
   */
  public static CreatePersonResponse transform(Exception e) {
    CreatePersonResponse wrapper = new CreatePersonResponse();
    wrapper.setHeader(HeaderFactory.transform(e));
    return wrapper;
  }


}
