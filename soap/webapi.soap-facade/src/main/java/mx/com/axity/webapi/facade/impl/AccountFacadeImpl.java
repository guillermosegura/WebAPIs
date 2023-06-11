package mx.com.axity.webapi.facade.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.axity.webapi.commons.base.HeaderDTO;
import mx.com.axity.webapi.commons.base.ResponseWrapperDTO;
import mx.com.axity.webapi.commons.dto.AccountDTO;
import mx.com.axity.webapi.commons.dto.PersonDTO;
import mx.com.axity.webapi.commons.exception.BusinessException;
import mx.com.axity.webapi.facade.AccountFacade;
import mx.com.axity.webapi.service.AccountService;

/**
 * Implementation class of the interface {@link mx.com.axity.webapi.facade.AccountFacade}
 * 
 * @author guillermo.segura@axity.com
 */
@Service
public class AccountFacadeImpl implements AccountFacade
{
  @Autowired
  private AccountService accountService;

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> createAccount( PersonDTO person, BigDecimal balance, LocalDateTime timestamp )
  {
    ResponseWrapperDTO<AccountDTO> response;
    try
    {
      response = this.accountService.createAccount( person, balance, timestamp );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ),
          null );
    }
    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> addBalance( int accountId, BigDecimal amount, LocalDateTime timestamp )
  {
    ResponseWrapperDTO<AccountDTO> response;
    try
    {
      response = this.accountService.addBalance( accountId, amount, timestamp );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ),
          null );
    }
    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> withdrawBalance( int accountId, BigDecimal amount, LocalDateTime timestamp )
  {
    ResponseWrapperDTO<AccountDTO> response;
    try
    {
      response = this.accountService.withdrawBalance( accountId, amount, timestamp );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ),
          null );
    }
    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<AccountDTO> getAccount( int accountId )
  {
    ResponseWrapperDTO<AccountDTO> response;
    try
    {
      response = this.accountService.getAccount( accountId );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<AccountDTO>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ),
          null );
    }
    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ResponseWrapperDTO<List<AccountDTO>> getAccounts( int personId )
  {
    ResponseWrapperDTO<List<AccountDTO>> response;
    try
    {
      response = this.accountService.getAccounts( personId );
    }
    catch( BusinessException e )
    {
      response = new ResponseWrapperDTO<List<AccountDTO>>( new HeaderDTO( e.getCode(), e.getMessage(), e.getDetails() ),
          null );
    }
    catch( Exception e )
    {
      response = new ResponseWrapperDTO<List<AccountDTO>>( new HeaderDTO( 1, e.getMessage(), e.getLocalizedMessage() ),
          null );
    }
    return response;
  }

}
