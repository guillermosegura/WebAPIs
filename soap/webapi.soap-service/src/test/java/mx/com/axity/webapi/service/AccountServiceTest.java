package mx.com.axity.webapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import mx.com.axity.webapi.soap.api.service.impl.AccountServiceImpl;

class AccountServiceTest {

  @Mock
  private PersonRepository personRepository;

  @Mock
  private AccountRepository accountRepository;

  @Mock
  private MovementTypeRepository movementTypeRepository;

  @Mock
  private AccountMovementRepository accountMovementRepository;

  @InjectMocks
  private AccountService accountService = new AccountServiceImpl();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateAccount() {
    // Mock input parameters
    PersonDTO person = new PersonDTO();
    person.setId(1);
    BigDecimal balance = BigDecimal.valueOf(5000);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    PersonDO entityPerson = this.createPersonDO(1, "John", "Doe", "john.doe@example.com", true);
    when(personRepository.findById(person.getId())).thenReturn(Optional.of(entityPerson));

    AccountDO savedAccount = new AccountDO();
    savedAccount.setId(1);
    savedAccount.setBalance(new BigDecimal("5000"));
    savedAccount.setActive(true);
    savedAccount.setPerson(this.createPersonDO(1, "John", "Doe", "john.doe@example.com", true));
    when(accountRepository.save(any(AccountDO.class))).thenReturn(savedAccount);

    MovementTypeDO movement = new MovementTypeDO();
    movement.setId(MovementType.ADD_BALANCE.getId());
    movement.setName("Add Balance ");
    when(movementTypeRepository.findById(MovementType.ADD_BALANCE.getId())).thenReturn(Optional.of(movement));

    AccountMovementDO accountMovement = new AccountMovementDO();
    accountMovement.setId(1);
    accountMovement.setAmount(balance);
    accountMovement.setMovementTimestamp(timestamp);
    accountMovement.setMovementType(movement);
    accountMovement.setAccount(savedAccount);
    when(accountMovementRepository.save(any(AccountMovementDO.class))).thenReturn(accountMovement);

    // Invoke the method under test
    ResponseWrapperDTO<AccountDTO> response = accountService.createAccount(person, balance, timestamp);

    // Verify the interactions with repositories
    verify(personRepository, times(1)).findById(person.getId());
    verify(accountRepository, times(1)).save(any(AccountDO.class));
    verify(accountMovementRepository, times(1)).save(any(AccountMovementDO.class));

    // Assert the response
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals("OK", response.getHeader().getMessage());
    assertEquals("Account created", response.getHeader().getDetails());
    assertNotNull(response.getBody());
  }

  @Test
  void testCreateAccount_PersonNotFound() {
    // Mock input parameters
    PersonDTO person = new PersonDTO();
    person.setId(1);
    BigDecimal balance = BigDecimal.valueOf(5000);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    when(personRepository.findById(person.getId()))
        .thenThrow(new BusinessException(100, "Record not found", "The person does not exists."));

    // Invoke the method under test

    BusinessException exception = Assertions.assertThrows(BusinessException.class,
        () -> accountService.createAccount(person, balance, timestamp));

    // Verify the interactions with repositories
    verify(personRepository, times(1)).findById(person.getId());

    // Assert the response
    assertNotNull(exception);
    assertEquals(100, exception.getCode());
    assertEquals("Record not found", exception.getMessage());
    assertEquals("The person does not exists.", exception.getDetails());
  }

  @Test
  void testCreateAccount_PersonInactive() {
    // Mock input parameters
    PersonDTO person = new PersonDTO();
    person.setId(1);
    BigDecimal balance = BigDecimal.valueOf(5000);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    PersonDO entityPerson = this.createPersonDO(1, "John", "Doe", "john.doe@example.com", false);
    when(personRepository.findById(person.getId())).thenReturn(Optional.of(entityPerson));

    // Invoke the method under test

    BusinessException exception = Assertions.assertThrows(BusinessException.class,
        () -> accountService.createAccount(person, balance, timestamp));

    // Verify the interactions with repositories
    verify(personRepository, times(1)).findById(person.getId());

    // Assert the response
    assertNotNull(exception);
    assertEquals(101, exception.getCode());
    assertEquals("Record not found", exception.getMessage());
    assertEquals("The person is inactive.", exception.getDetails());
  }

  @Test
  void testAddBalance() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(1000);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(true);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    AccountDO savedAccount = new AccountDO();
    savedAccount.setId(1);
    savedAccount.setBalance(new BigDecimal("11000"));
    savedAccount.setActive(true);
    savedAccount.setPerson(this.createPersonDO(1, "John", "Doe", "john.doe@example.com", true));
    when(accountRepository.save(any(AccountDO.class))).thenReturn(savedAccount);

    MovementTypeDO movement = new MovementTypeDO();
    movement.setId(MovementType.ADD_BALANCE.getId());
    movement.setName("Add Balance ");
    when(movementTypeRepository.findById(MovementType.ADD_BALANCE.getId())).thenReturn(Optional.of(movement));

    AccountMovementDO accountMovement = new AccountMovementDO();
    accountMovement.setId(1);
    accountMovement.setAmount(amount);
    accountMovement.setMovementTimestamp(timestamp);
    accountMovement.setMovementType(movement);
    accountMovement.setAccount(savedAccount);
    List<AccountMovementDO> movements = Arrays.asList(accountMovement);
    when(accountMovementRepository.findLast5Movements(1)).thenReturn(movements);

    // Invoke the method under test
    ResponseWrapperDTO<AccountDTO> response = accountService.addBalance(accountId, amount, timestamp);

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);
    verify(accountRepository, times(1)).save(any(AccountDO.class));
    verify(accountMovementRepository, times(1)).findLast5Movements(1);
    verify(accountMovementRepository, times(1)).save(any(AccountMovementDO.class));

    // Assert the response
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals("OK", response.getHeader().getMessage());
    assertEquals("Balance account updated", response.getHeader().getDetails());
    assertNotNull(response.getBody());
    assertEquals(new BigDecimal("11000"), response.getBody().getBalance());
  }

  @Test
  void testAddBalance_accountNotFound() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(0);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities

    when(accountRepository.findById(accountId))
        .thenThrow(new BusinessException(100, "Record not found", "Account not found"));

    // Invoke the method under test
    BusinessException businessException =
        Assertions.assertThrows(BusinessException.class, () -> accountService.addBalance(accountId, amount, timestamp));

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);

    // Assert the response
    assertNotNull(businessException);
    assertEquals(100, businessException.getCode());
    assertEquals("Record not found", businessException.getMessage());
    assertEquals("Account not found", businessException.getDetails());
  }

  @Test
  void testAddBalance_accountInactive() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(0);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    // Mock repositories and entities
    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(false);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    // Invoke the method under test
    BusinessException businessException =
        Assertions.assertThrows(BusinessException.class, () -> accountService.addBalance(accountId, amount, timestamp));

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);

    // Assert the response
    assertNotNull(businessException);
    assertEquals(101, businessException.getCode());
    assertEquals("Record not found", businessException.getMessage());
    assertEquals("The account is inactive.", businessException.getDetails());
  }

  @Test
  void testAddBalance_amountInvalidZero() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(0);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(true);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    // Invoke the method under test
    BusinessException businessException =
        Assertions.assertThrows(BusinessException.class, () -> accountService.addBalance(accountId, amount, timestamp));

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);

    // Assert the response
    assertNotNull(businessException);
    assertEquals(200, businessException.getCode());
    assertEquals("Invalid value", businessException.getMessage());
    assertEquals("The amount is zero.", businessException.getDetails());
  }

  @Test
  void testAddBalance_amountInvalidNegative() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(-1);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(true);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    // Invoke the method under test
    BusinessException businessException =
        Assertions.assertThrows(BusinessException.class, () -> accountService.addBalance(accountId, amount, timestamp));

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);

    // Assert the response
    assertNotNull(businessException);
    assertEquals(200, businessException.getCode());
    assertEquals("Invalid value", businessException.getMessage());
    assertEquals("The amount is negative.", businessException.getDetails());
  }

  @Test
  void testWithdrawBalance() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(1000);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(true);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    AccountDO savedAccount = new AccountDO();
    savedAccount.setId(1);
    savedAccount.setBalance(new BigDecimal("9000"));
    savedAccount.setActive(true);
    savedAccount.setPerson(this.createPersonDO(1, "John", "Doe", "john.doe@example.com", true));
    when(accountRepository.save(any(AccountDO.class))).thenReturn(savedAccount);

    MovementTypeDO movement = new MovementTypeDO();
    movement.setId(MovementType.BALANCE_WITHDRAWAL.getId());
    movement.setName("Balance withdrawal");
    when(movementTypeRepository.findById(MovementType.BALANCE_WITHDRAWAL.getId())).thenReturn(Optional.of(movement));

    AccountMovementDO accountMovement = new AccountMovementDO();
    accountMovement.setId(1);
    accountMovement.setAmount(amount);
    accountMovement.setMovementTimestamp(timestamp);
    accountMovement.setMovementType(movement);
    accountMovement.setAccount(savedAccount);
    List<AccountMovementDO> movements = Arrays.asList(accountMovement);
    when(accountMovementRepository.findLast5Movements(1)).thenReturn(movements);

    // Invoke the method under test
    ResponseWrapperDTO<AccountDTO> response = accountService.withdrawBalance(accountId, amount, timestamp);

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);
    verify(accountRepository, times(1)).save(any(AccountDO.class));
    verify(accountMovementRepository, times(1)).findLast5Movements(1);
    verify(accountMovementRepository, times(1)).save(any(AccountMovementDO.class));

    // Assert the response
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals("OK", response.getHeader().getMessage());
    assertEquals("Balance account updated", response.getHeader().getDetails());
    assertNotNull(response.getBody());
    assertEquals(new BigDecimal("9000"), response.getBody().getBalance());
  }

  @Test
  void testWithdrawBalance_insufficientBalance() {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(1000);
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("500"));
    account.setActive(true);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    // Invoke the method under test
    BusinessException businessException = Assertions.assertThrows(BusinessException.class,
        () -> accountService.withdrawBalance(accountId, amount, timestamp));

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);

    // Assert the response
    assertNotNull(businessException);
    assertEquals(201, businessException.getCode());
    assertEquals("Insufficient balance", businessException.getMessage());
    assertEquals("The amount is greater than the balance account.", businessException.getDetails());
  }

  @Test
  void testGetAccount() {
    // Mock input parameters
    int accountId = 1;
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    PersonDO entityPerson = this.createPersonDO(1, "John", "Doe", "john.doe@example.com", true);

    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(true);
    account.setPerson(entityPerson);
    account.setCreatedTimestamp(timestamp);
    account.setUpdatedTimestamp(timestamp);
    when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

    MovementTypeDO movement = new MovementTypeDO();
    movement.setId(MovementType.ADD_BALANCE.getId());
    movement.setName("Add Balance ");
    when(movementTypeRepository.findById(MovementType.ADD_BALANCE.getId())).thenReturn(Optional.of(movement));

    AccountMovementDO accountMovement = new AccountMovementDO();
    accountMovement.setId(1);
    accountMovement.setAmount(new BigDecimal("10000"));
    accountMovement.setMovementTimestamp(timestamp);
    accountMovement.setMovementType(movement);
    accountMovement.setAccount(account);
    List<AccountMovementDO> movements = Arrays.asList(accountMovement);
    when(accountMovementRepository.findLast5Movements(1)).thenReturn(movements);

    // Invoke the method under test
    ResponseWrapperDTO<AccountDTO> response = accountService.getAccount(accountId);

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findById(accountId);
    verify(accountMovementRepository, times(1)).findLast5Movements(1);

    // Assert the response
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals("OK", response.getHeader().getMessage());
    assertEquals("", response.getHeader().getDetails());
    assertNotNull(response.getBody());
    assertEquals(new BigDecimal("10000"), response.getBody().getBalance());
  }

  @Test
  void testGetAccounts() {
    // Mock input parameters
    int personId = 1;
    int accountId = 1;
    Date timestamp = Calendar.getInstance().getTime();

    // Mock repositories and entities
    PersonDO entityPerson = this.createPersonDO(1, "John", "Doe", "john.doe@example.com", true);

    AccountDO account = new AccountDO();
    account.setId(1);
    account.setBalance(new BigDecimal("10000"));
    account.setActive(true);
    account.setPerson(entityPerson);
    account.setCreatedTimestamp(timestamp);
    account.setUpdatedTimestamp(timestamp);
    when(accountRepository.findByPersonId(personId)).thenReturn(Arrays.asList(account));

    // Invoke the method under test
    ResponseWrapperDTO<List<AccountDTO>> response = accountService.getAccounts(personId);

    // Verify the interactions with repositories
    verify(accountRepository, times(1)).findByPersonId(personId);

    // Assert the response
    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertEquals("OK", response.getHeader().getMessage());
    assertEquals("", response.getHeader().getDetails());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
    assertEquals(1, response.getBody().size());
  }

  private PersonDO createPersonDO(int id, String name, String lastname, String email, boolean active) {
    PersonDO personDO = new PersonDO();
    personDO.setId(id);
    personDO.setName(name);
    personDO.setLastname(lastname);
    personDO.setEmail(email);
    personDO.setActive(active);
    return personDO;
  }

}
