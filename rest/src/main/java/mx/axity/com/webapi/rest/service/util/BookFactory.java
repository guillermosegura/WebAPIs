package mx.axity.com.webapi.rest.service.util;

import mx.axity.com.webapi.rest.commons.dto.BookDTO;
import mx.axity.com.webapi.rest.model.BookDO;

/**
 * Utility class for transforming Book types.
 * 
 * <p>
 * This utility class provides static methods for transforming instances of
 * {@link mx.axity.com.webapi.rest.commons.dto.BookDTO} into
 * {@link mx.axity.com.webapi.rest.commons.BookDO} and vice versa.
 * </p>
 * 
 * <p>
 * The class is marked as final to prevent inheritance and promote immutability. It cannot be
 * instantiated, and all methods are static.
 * </p>
 * 
 * <p>
 * Note: This utility class assumes that the provided DTO and DO classes have matching properties
 * with appropriate getters and setters. The transformation is performed by copying the values from
 * one object to another.
 * </p>
 * 
 * @author guillermo.segura@axity.com
 * @see mx.axity.com.webapi.rest.commons.dto.BookDTO
 * @see mx.axity.com.webapi.rest.commons.BookDO
 */
public final class BookFactory {

  /**
   * Transforms an instance of {@link mx.axity.com.webapi.rest.commons.dto.BookDTO} into a
   * {@link mx.axity.com.webapi.rest.commons.BookDO}.
   * 
   * @param dto the BookDTO instance to transform
   * @return the transformed BookDO instance, or null if the input is null
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
   * Transforms an instance of {@link mx.axity.com.webapi.rest.commons.BookDO} into a
   * {@link mx.axity.com.webapi.rest.commons.dto.BookDTO}.
   * 
   * @param entity the BookDO instance to transform
   * @return the transformed BookDTO instance, or null if the input is null
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
