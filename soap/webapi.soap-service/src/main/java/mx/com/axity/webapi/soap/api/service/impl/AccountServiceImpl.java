package mx.com.axity.webapi.soap.api.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.axity.webapi.soap.api.commons.base.HeaderDTO;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.AccountDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;
import mx.com.axity.webapi.soap.api.commons.enums.MovementType;
import mx.com.axity.webapi.soap.api.commons.exception.BusinessException;
import mx.com.axity.webapi.soap.api.model.entity.AccountDO;
import mx.com.axity.webapi.soap.api.model.entity.AccountMovementDO;
import mx.com.axity.webapi.soap.api.model.entity.MovementTypeDO;
import mx.com.axity.webapi.soap.api.model.entity.PersonDO;
import mx.com.axity.webapi.soap.api.persistence.repository.AccountMovementRepository;
import mx.com.axity.webapi.soap.api.persistence.repository.AccountRepository;
import mx.com.axity.webapi.soap.api.persistence.repository.MovementTypeRepository;
import mx.com.axity.webapi.soap.api.persistence.repository.PersonRepository;
import mx.com.axity.webapi.soap.api.service.AccountService;
import mx.com.axity.webapi.soap.api.service.util.AccountDOFactory;

/**
 * Implementation class for {@link mx.com.axity.webapi.soap.api.service.AccountService}
 * 
 * @author guillermo.segura@axity.com
 */
@Service
public class AccountServiceImpl implements AccountService
{
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private MovementTypeRepository movementTypeRepository;

  @Autowired
  private AccountMovementRepository accountMovementRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> createAccount( PersonDTO person, BigDecimal balance, Date timestamp )
  {
    PersonDO entityPerson = findPersonById( person );
    // Create a new account
    AccountDO account = new AccountDO();
    account.setPerson( entityPerson );
    account.setBalance( balance );
    account.setActive( true );
    account.setCreatedTimestamp( timestamp );
    account.setUpdatedTimestamp( timestamp );
    AccountDO savedAccount = accountRepository.save( account );

    AccountMovementDO movement = this.addBalance( balance, timestamp, savedAccount );

    // Transform and return the account DTO
    AccountDTO accountDTO = AccountDOFactory.transform( savedAccount, Arrays.asList( movement ) );

    ResponseWrapperDTO<AccountDTO> response = new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Account created" ),
        accountDTO );

    return response;
  }

  private AccountMovementDO addBalance( BigDecimal amount, Date timestamp, AccountDO savedAccount )
  {
    MovementTypeDO movementType = this.movementTypeRepository.findById( MovementType.ADD_BALANCE.getId() ).get();
    AccountMovementDO movement = new AccountMovementDO();
    movement.setAccount( savedAccount );
    movement.setAmount( amount );
    movement.setMovementTimestamp( timestamp );
    movement.setMovementType( movementType );

    movement = this.accountMovementRepository.save( movement );

    return movement;
  }

  private PersonDO findPersonById( PersonDTO person )
  {
    int personId = person.getId();
    PersonDO entityPerson = this.personRepository.findById( personId )
        .orElseThrow( () -> new BusinessException( "Record not found", 100, "The person does not exists." ) );
    if( !entityPerson.isActive() )
    {
      throw new BusinessException( "Record not found", 101, "The person is inactive." );
    }
    return entityPerson;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> addBalance( int accountId, BigDecimal amount, Date timestamp )
  {
    AccountDO account = this.findAccount( accountId );

    this.validateAmount( amount );

    account.setBalance( account.getBalance().add( amount ) );
    account.setUpdatedTimestamp( timestamp );

    account = this.accountRepository.save( account );
    this.addBalance( amount, timestamp, account );

    List<AccountMovementDO> movements = this.accountMovementRepository.findLast5Movements();

    // Transform and return the account DTO
    AccountDTO accountDTO = AccountDOFactory.transform( account, movements );

    ResponseWrapperDTO<AccountDTO> response = new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Balance account updated" ),
        accountDTO );

    return response;
  }

  private void validateAmount( BigDecimal amount )
  {
    if( amount.doubleValue() <= 0.0 )
    {
      throw new BusinessException( "Invalid value", 200, "The amount is invalid." );
    }
  }

  private AccountDO findAccount( int accountId )
  {
    AccountDO account = this.accountRepository.findById( accountId )
        .orElseThrow( () -> new BusinessException( "Record not found", 100, "Account not found" ) );
    if( !account.isActive() )
    {
      throw new BusinessException( "Record not found", 101, "The account is inactive." );
    }
    return account;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> withdrawBalance( int accountId, BigDecimal amount, Date timestamp )
  {
    AccountDO account = this.findAccount( accountId );

    this.validateAmount( amount );

    if( account.getBalance().compareTo( amount ) < 0 )
    {
      throw new BusinessException( "Insufficient balance", 201, "The amount is greater than the balance account." );
    }

    account.setBalance( account.getBalance().subtract( amount ) );
    account.setUpdatedTimestamp( timestamp );

    account = this.accountRepository.save( account );
    this.withdrawBalance( amount, timestamp, account );

    List<AccountMovementDO> movements = this.accountMovementRepository.findLast5Movements();

    // Transform and return the account DTO
    AccountDTO accountDTO = AccountDOFactory.transform( account, movements );

    ResponseWrapperDTO<AccountDTO> response = new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "Balance account updated" ),
        accountDTO );

    return response;
  }

  private AccountMovementDO withdrawBalance( BigDecimal amount, Date timestamp, AccountDO savedAccount )
  {
    MovementTypeDO movementType = this.movementTypeRepository.findById( MovementType.BALANCE_WITHDRAWAL.getId() ).get();
    AccountMovementDO movement = new AccountMovementDO();
    movement.setAccount( savedAccount );
    movement.setAmount( amount );
    movement.setMovementTimestamp( timestamp );
    movement.setMovementType( movementType );

    movement = this.accountMovementRepository.save( movement );

    return movement;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> getAccount( int accountId )
  {
    AccountDO account = this.findAccount( accountId );
    List<AccountMovementDO> movements = this.accountMovementRepository.findLast5Movements();

    // Transform and return the account DTO
    AccountDTO accountDTO = AccountDOFactory.transform( account, movements );

    ResponseWrapperDTO<AccountDTO> response = new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "" ), accountDTO );

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<List<AccountDTO>> getAccounts( int personId )
  {
    List<AccountDO> accounts = this.accountRepository.findByPersonId( personId );

    ResponseWrapperDTO<List<AccountDTO>> response = new ResponseWrapperDTO<>( new HeaderDTO( 0, "OK", "" ),
        accounts.stream().map( AccountDOFactory::transform ).collect( Collectors.toList() ) );

    return response;
  }

}
