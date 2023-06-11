package mx.com.axity.webapi.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mx.com.axity.webapi.commons.base.HeaderDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.AccountDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;
import mx.com.axity.webapi.facade.impl.AccountFacadeImpl;
import mx.com.axity.webapi.service.AccountService;

class AccountFacadeTest
{
  @Mock
  private AccountService accountService;

  @InjectMocks
  private AccountFacade accountFacade = new AccountFacadeImpl();
  
  @BeforeEach
  void setUp() {
      MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateAccount_Success()
  {
    // Mock input parameters
    PersonDTO person = new PersonDTO();
    BigDecimal balance = BigDecimal.valueOf(5000);
    LocalDateTime timestamp = LocalDateTime.now();
    
    // Mock response
    AccountDTO accountDTO = new AccountDTO();
    ResponseWrapperDTO<AccountDTO> expectedResponse = new ResponseWrapperDTO<>(new HeaderDTO(), accountDTO);
    
    // Configure mock behavior
    when(accountService.createAccount(person, balance, timestamp)).thenReturn(expectedResponse);
    
    // Invoke the method under test
    ResponseWrapperDTO<AccountDTO> actualResponse = accountFacade.createAccount(person, balance, timestamp);
    
    // Verify the mock interactions and assert the result
    verify(accountService, times(1)).createAccount(person, balance, timestamp);
    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  void testAddBalance()
  {
    // Mock input parameters
    int accountId = 1;
    BigDecimal amount = BigDecimal.valueOf(1000);
    LocalDateTime timestamp = LocalDateTime.now();
    
    // Mock response
    AccountDTO accountDTO = new AccountDTO();
    ResponseWrapperDTO<AccountDTO> expectedResponse = new ResponseWrapperDTO<>(new HeaderDTO(), accountDTO);
    
    // Configure mock behavior
    when(accountService.addBalance(accountId, amount, timestamp)).thenReturn(expectedResponse);
    
    // Invoke the method under test
    ResponseWrapperDTO<AccountDTO> actualResponse = accountFacade.addBalance(accountId, amount, timestamp);
    
    // Verify the mock interactions and assert the result
    verify(accountService, times(1)).addBalance(accountId, amount, timestamp);
    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  void testWithdrawBalance()
  {
    fail( "Not yet implemented" ); 
  }

  @Test
  void testGetAccount()
  {
    fail( "Not yet implemented" ); 
  }

  @Test
  void testGetAccounts()
  {
    // Mock input parameter
    int personId = 1;
    
    // Mock response
    List<AccountDTO> accountList = new ArrayList<>();
    ResponseWrapperDTO<List<AccountDTO>> expectedResponse = new ResponseWrapperDTO<>(new HeaderDTO(), accountList);
    
    // Configure mock behavior
    when(accountService.getAccounts(personId)).thenReturn(expectedResponse);
    
    // Invoke the method under test
    ResponseWrapperDTO<List<AccountDTO>> actualResponse = accountFacade.getAccounts(personId);
    
    // Verify the mock interactions and assert the result
    verify(accountService, times(1)).getAccounts(personId);
    assertEquals(expectedResponse, actualResponse);
  }

}
