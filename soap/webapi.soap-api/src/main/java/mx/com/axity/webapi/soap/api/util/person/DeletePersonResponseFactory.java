package mx.com.axity.webapi.soap.api.util.person;

import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.person.DeletePersonResponse;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.person.DeletePersonResponse}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class DeletePersonResponseFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO<PersonDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.DeletePersonResponse}
   * 
   * @param response
   * @return
   */
  public static DeletePersonResponse transform(ResponseWrapperDTO<PersonDTO> response) {
    DeletePersonResponse wrapper = new DeletePersonResponse();
    wrapper.setHeader(HeaderFactory.transform(response.getHeader()));
    wrapper.setBody(PersonFactory.transform(response.getBody()));

    return wrapper;
  }

  /**
   * Transforms a {@link java.lang.Exception} into a
   * {@link mx.com.axity.webapi.soap.api.ws.person.DeletePersonResponse}
   * 
   * @param e
   * @return
   */
  public static DeletePersonResponse transform(Exception e) {
    DeletePersonResponse wrapper = new DeletePersonResponse();
    wrapper.setHeader(HeaderFactory.transform(e));
    return wrapper;
  }


}
