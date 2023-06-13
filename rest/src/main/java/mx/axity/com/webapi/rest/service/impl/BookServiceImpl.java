package mx.axity.com.webapi.rest.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import mx.axity.com.webapi.rest.commons.BookDTO;
import mx.axity.com.webapi.rest.commons.PaginatedDTO;
import mx.axity.com.webapi.rest.commons.exception.BusinessException;
import mx.axity.com.webapi.rest.model.BookDO;
import mx.axity.com.webapi.rest.persistence.BookRepository;
import mx.axity.com.webapi.rest.service.BookService;
import mx.axity.com.webapi.rest.service.util.BookFactory;

/**
 * Implementation of the BookService interface. This class provides the implementation for managing
 * books.
 *
 * <p>
 * <b>Note:</b> This implementation relies on a BookRepository for data access operations. It
 * encapsulates the necessary business logic to create, retrieve, update, and delete books.
 * 
 * <p>
 * <b>Important:</b> This class is not thread-safe and should be used in a thread-safe manner or
 * scoped appropriately in a Spring context.
 *
 * @author guillermo.segura@axity.com
 */
@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private Validator validator;

  /**
   * {@inheritDoc}
   */
  @Override
  public BookDTO create(BookDTO book) {

    this.validateRequiredFields(book);

    Optional<BookDO> saved = this.bookRepository.getByTitle(book.getTitle());
    if (saved.isPresent()) {
      throw new BusinessException("Registry found", 100);
    }
    BookDO entity = BookFactory.transform(book);


    entity = this.bookRepository.save(entity);

    return BookFactory.transform(entity);
  }

  private void validateRequiredFields(BookDTO book) {
    Set<ConstraintViolation<BookDTO>> violations = validator.validate(book);
    if (!violations.isEmpty()) {
      String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
      throw new BusinessException(message, 1);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BookDTO getById(int bookId) {
    BookDO entity = this.bookRepository.findById(bookId).orElse(null);

    return BookFactory.transform(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedDTO<BookDTO> getAll(int size, int offset) {

    int pageNumber = offset / size;
    PageRequest pageRequest = PageRequest.of(pageNumber, size, Sort.by("id"));

    Page<BookDO> page = this.bookRepository.findAll(pageRequest);

    List<BookDTO> items = page.getContent().stream().map(BookFactory::transform).collect(Collectors.toList());

    PaginatedDTO<BookDTO> result =
        new PaginatedDTO<>(items, size, offset, page.getTotalElements(), page.getTotalPages());

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BookDTO update(BookDTO book) {
    BookDO entity = this.bookRepository.findById(book.getId()).orElse(null);
    if (entity != null) {
      entity.setAuthor(book.getAuthor());
      entity.setGenre(book.getGenre());
      entity.setTitle(book.getTitle());
      entity = this.bookRepository.save(entity);
    }

    return BookFactory.transform(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean delete(int bookId) {
    BookDO entity = this.bookRepository.findById(bookId).orElse(null);
    boolean deleted = false;
    if (entity != null) {
      this.bookRepository.delete(entity);
      deleted = true;
    }

    return deleted;
  }

}
