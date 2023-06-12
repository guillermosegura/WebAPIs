package mx.com.axity.webapi.soap.api.util.account;

import mx.com.axity.webapi.soap.api.commons.dto.AccountDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.ws.account.Account;
import mx.com.axity.webapi.soap.api.ws.account.Person;

/**
 * Factory class for {@link {@link mx.com.axity.webapi.soap.api.ws.person.PersonList}}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class AccountFactory {

  /***
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.dto.AccountDTO} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.Account}
   * 
   * @param dto
   * @return
   */
  public static Account transform(AccountDTO dto) {
    Account account = null;
    if (dto != null) {
      account = new Account();
      account.setId(dto.getId());
      account.setBalance(dto.getBalance());
      account.setPerson(transform(dto.getPerson()));
      account.setMovements(AccountMovementListFactory.transform(dto.getMovements()));
    }
    return account;
  }

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.ws.account.Account} into a
   * {@link mx.com.axity.webapi.soap.api.commons.dto.AccountDTO}
   * 
   * @param account
   * @return
   */
  public static AccountDTO transform(Account account) {
    AccountDTO dto = null;
    if (account != null) {
      dto = new AccountDTO();
      dto.setId(account.getId());
      dto.setBalance(account.getBalance());
      dto.setPerson(transform(account.getPerson()));
    }
    return dto;
  }

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.dto.PersonDTO} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.Person}
   * 
   * @param dto
   * @return
   */
  public static Person transform(PersonDTO dto) {
    Person person = null;
    if (dto != null) {
      person = new Person();
      person.setId(dto.getId());
      person.setName(dto.getName());
      person.setLastname(dto.getLastname());
      person.setEmail(dto.getEmail());
      person.setActive(dto.isActive());
    }
    return person;
  }

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.ws.account.Person} into a
   * {@link mx.com.axity.webapi.soap.api.commons.dto.PersonDTO}
   * 
   * 
   * @param dto
   * @return
   */
  public static PersonDTO transform(Person person) {
    PersonDTO dto = null;
    if (person != null) {
      dto = new PersonDTO();
      dto.setId(person.getId());
      dto.setName(person.getName());
      dto.setLastname(person.getLastname());
      dto.setEmail(person.getEmail());
      dto.setActive(person.isActive());
    }
    return dto;
  }
}
