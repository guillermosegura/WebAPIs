package mx.axity.com.webapi.rest.service.util;

import mx.axity.com.webapi.rest.commons.BookDTO;
import mx.axity.com.webapi.rest.model.BookDO;

/**
 * Utility class for transforming Book types.
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class BookFactory {

  /**
   * Transforms an instance of {@link mx.axity.com.webapi.rest.model.BookDTO} into a
   * {@link mx.axity.com.webapi.rest.commons.BookDO}
   * 
   * @param dto
   * @return
   */
  public static BookDO transform(BookDTO dto) {
    BookDO entity = null;
    if (dto != null) {
      entity = new BookDO();
      entity.setId(dto.getId());
      entity.setAuthor(dto.getAuthor());
      entity.setGenre(dto.getGenre());
      entity.setTitle(dto.getTitle());
    }
    return entity;
  }

  /**
   * Transforms an instance of {@link mx.axity.com.webapi.rest.model.BookDO} into a
   * {@link mx.axity.com.webapi.rest.commons.BookDTO}
   * 
   * @param entity
   * @return
   */
  public static BookDTO transform(BookDO entity) {
    BookDTO dto = null;
    if (entity != null) {
      dto = new BookDTO();
      dto.setId(entity.getId());
      dto.setAuthor(entity.getAuthor());
      dto.setGenre(entity.getGenre());
      dto.setTitle(entity.getTitle());
    }
    return dto;
  }
}
