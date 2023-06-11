package mx.com.axity.webapi.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mx.com.axity.webapi.commons.dto.AccountDTO;
import mx.com.axity.webapi.commons.dto.AccountMovementDTO;
import mx.com.axity.webapi.commons.enums.MovementType;
import mx.com.axity.webapi.model.entity.AccountDO;
import mx.com.axity.webapi.model.entity.AccountMovementDO;

public final class AccountDOFactory
{
  public static AccountDTO transform( AccountDO accountDO )
  {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId( accountDO.getId() );
    accountDTO.setBalance( accountDO.getBalance() );
    accountDTO.setMovements( new ArrayList<>() );
    accountDTO.setPerson( PersonDTOFactory.transform( accountDO.getPerson() ) );
    return accountDTO;
  }

  public static AccountDTO transform( AccountDO accountDO, List<AccountMovementDO> movements )
  {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId( accountDO.getId() );
    accountDTO.setBalance( accountDO.getBalance() );
    accountDTO.setMovements( movements.stream().map( AccountDOFactory::transformMovement ).collect( Collectors.toList() ) );
    accountDTO.setPerson( PersonDTOFactory.transform( accountDO.getPerson() ) );
    return accountDTO;
  }

  private static AccountMovementDTO transformMovement( AccountMovementDO movementDO )
  {
    AccountMovementDTO movementDTO = new AccountMovementDTO();
    movementDTO.setId( movementDO.getId() );
    movementDTO.setAmount( movementDO.getAmount() );
    movementDTO.setMovementType( MovementType.fromId( movementDO.getId() ) );
    movementDTO.setMovementTimestamp( movementDO.getMovementTimestamp());
    return movementDTO;
  }
}
