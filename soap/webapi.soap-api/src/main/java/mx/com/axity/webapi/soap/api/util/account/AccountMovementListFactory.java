package mx.com.axity.webapi.soap.api.util.account;

import java.util.List;
import mx.com.axity.webapi.soap.api.commons.dto.AccountMovementDTO;
import mx.com.axity.webapi.soap.api.ws.account.AccountMovementList;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.account.AccountMovementList}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class AccountMovementListFactory {

  /***
   * Transforms a {@link java.util.List<AccountMovementDTO>} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.AccountMovementList}
   * 
   * @param movements
   * @return
   */
  public static AccountMovementList transform(List<AccountMovementDTO> movements) {
    final AccountMovementList accountMovementList = new AccountMovementList();

    if (movements != null) {
      movements.forEach(accountMovement -> accountMovementList.getAccountMovement()
          .add(AccountMovementFactory.transform(accountMovement)));

    }

    return accountMovementList;
  }
}
