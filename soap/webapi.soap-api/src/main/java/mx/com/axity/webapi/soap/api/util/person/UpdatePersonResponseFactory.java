package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.UpdatePersonResponse;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.UpdatePersonResponse}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class UpdatePersonResponseFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO<PersonDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.UpdatePersonResponse}
   * 
   * @param response
   * @return
   */
  public static UpdatePersonResponse transform(ResponseWrapperDTO<PersonDTO> response) {
    UpdatePersonResponse wrapper = new UpdatePersonResponse();
    wrapper.setHeader(HeaderFactory.transform(response.getHeader()));
    wrapper.setBody(PersonFactory.transform(response.getBody()));

    return wrapper;
  }

  /**
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.UpdatePersonResponse}
   * 
   * @param e
   * @return
   */
  public static UpdatePersonResponse transform(Exception e) {
    UpdatePersonResponse wrapper = new UpdatePersonResponse();
    wrapper.setHeader(HeaderFactory.transform(e));
    return wrapper;
  }

}
