package mx.com.axity.webapi.soap.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import mx.com.axity.webapi.soap.api.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.soap.api.commons.dto.AccountDTO;
import mx.com.axity.webapi.soap.api.commons.dto.PersonDTO;

public interface AccountService
{
  /**
   * Method to create an account and associate to a person.
   * 
   * @param person
   * @param balance
   * @param timestamp
   * @return
   */
  ResponseWrapperDTO<AccountDTO> createAccount( PersonDTO person, BigDecimal balance, Date timestamp );

  /***
   * Method to add balance to an existing and active account.
   * 
   * @param accountId
   * @param amount
   * @param timestamp
   * @return
   */
  ResponseWrapperDTO<AccountDTO> addBalance( int accountId, BigDecimal amount, Date timestamp );

  /***
   * Method to withdraw balance to an existing and active account.
   * 
   * @param accountId
   * @param amount
   * @param timestamp
   * @return
   */
  ResponseWrapperDTO<AccountDTO> withdrawBalance( int accountId, BigDecimal amount, Date timestamp );

  /**
   * Method to get the account by id.
   * 
   * @param accountId
   * @return
   */
  ResponseWrapperDTO<AccountDTO> getAccount( int accountId );

  /***
   * Method to get the accounts of a given person id.
   * 
   * @param personId
   * @return
   */
  ResponseWrapperDTO<List<AccountDTO>> getAccounts( int personId );

}
