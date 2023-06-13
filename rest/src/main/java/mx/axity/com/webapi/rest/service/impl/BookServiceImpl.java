package mx.axity.com.webapi.rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import mx.axity.com.webapi.rest.commons.BookDTO;
import mx.axity.com.webapi.rest.commons.PaginatedDTO;
import mx.axity.com.webapi.rest.model.BookDO;
import mx.axity.com.webapi.rest.persistence.BookRepository;
import mx.axity.com.webapi.rest.service.BookService;
import mx.axity.com.webapi.rest.service.util.BookFactory;

/**
 * Implementation class for {@link mx.axity.com.webapi.rest.service.BookService}
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public BookDTO create(BookDTO book) {
    BookDO entity = BookFactory.transform(book);

    // TODO agregar manejo de errores y validaciones
    entity = this.bookRepository.save(entity);

    return BookFactory.transform(entity);
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
