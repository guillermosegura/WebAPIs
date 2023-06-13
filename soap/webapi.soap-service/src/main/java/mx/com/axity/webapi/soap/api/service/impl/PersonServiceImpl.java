package mx.com.axity.webapi.soap.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import mx.com.axity.webapi.soap.api.commons.base.HeaderDTO;
import mx.com.axity.webapi.soap.api.commons.base.PaginatedDTO;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.commons.exception.BusinessException;
import mx.com.axity.webapi.soap.api.model.entity.PersonDO;
import mx.com.axity.webapi.soap.api.persistence.repository.PersonRepository;
import mx.com.axity.webapi.soap.api.service.PersonService;
import mx.com.axity.webapi.soap.api.service.util.PersonDTOFactory;

/**
 * Implementation class for the {@link mx.com.axity.webapi.soap.api.service.PersonService}
 * interface.
 * 
 * @author guillermo.segura@axity.com
 */
@Service
public class PersonServiceImpl implements PersonService {
  @Autowired
  private PersonRepository personRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> createPerson(PersonDTO person) {
    PersonDO entity = new PersonDO();
    entity.setName(person.getName());
    entity.setLastname(person.getLastname());
    entity.setEmail(person.getEmail());
    entity.setActive(true);
    entity = personRepository.save(entity);

    person.setId(entity.getId());

    return new ResponseWrapperDTO<>(new HeaderDTO(0, "OK", "Record created"), person);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> getPerson(Integer id) {
    PersonDO entity = this.findPersonById(id);

    return new ResponseWrapperDTO<>(new HeaderDTO(0, "OK", "Record found"), PersonDTOFactory.transform(entity));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PaginatedDTO<PersonDTO>> getPersons(int size, int offset) {
    int pageNumber = offset / size;
    PageRequest pageRequest = PageRequest.of(pageNumber, size, Sort.by("id"));
    Page<PersonDO> page = this.personRepository.findAllByActiveIsTrue(pageRequest);

    long count = this.personRepository.count();
    List<PersonDO> s = page.getContent();

    List<PersonDTO> items = page.getContent().stream().map(PersonDTOFactory::transform).collect(Collectors.toList());

    PaginatedDTO<PersonDTO> result =
        new PaginatedDTO<PersonDTO>(items, size, offset, page.getTotalElements(), page.getTotalPages());

    return new ResponseWrapperDTO<PaginatedDTO<PersonDTO>>(new HeaderDTO(0, "OK", "Records found"), result);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> updatePerson(PersonDTO person) {
    PersonDO entity = this.findPersonById(person.getId());

    entity.setName(person.getName());
    entity.setLastname(person.getLastname());
    entity.setEmail(person.getEmail());
    this.personRepository.save(entity);

    return new ResponseWrapperDTO<>(new HeaderDTO(0, "OK", "Record updated"), null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<PersonDTO> deletePerson(Integer id) {
    PersonDO entity = this.findPersonById(id);;

    if (!entity.isActive()) {
      throw new BusinessException(101, "Record already deleted");
    }

    entity.setActive(false);
    entity = this.personRepository.save(entity);

    return new ResponseWrapperDTO<>(new HeaderDTO(0, "OK", "Record deleted"), null);
  }

  private PersonDO findPersonById(Integer id) {
    return this.personRepository.findById(id)
        .orElseThrow(() -> new BusinessException(100, "Record not found", "The person does not exists."));
  }

}
