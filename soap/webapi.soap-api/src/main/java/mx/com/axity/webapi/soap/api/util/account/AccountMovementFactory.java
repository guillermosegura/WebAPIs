package mx.com.axity.webapi.soap.api.util.account;

import java.util.EnumMap;
import java.util.Map;
import mx.com.axity.webapi.soap.api.commons.dto.AccountMovementDTO;
import mx.com.axity.webapi.soap.api.util.XMLGregorianCalendarFactory;
import mx.com.axity.webapi.soap.api.ws.account.AccountMovement;
import mx.com.axity.webapi.soap.api.ws.account.MovementType;

/**
 * Factory class for {@link mx.com.axity.webapi.soap.api.ws.account.AccountMovement}
 * 
 * @author guillermo.segura@axity.com
 *
 */
public final class AccountMovementFactory {

  /**
   * Transforms a {@link mx.com.axity.webapi.soap.api.commons.dto.AccountMovementDTO} into a
   * {@link mx.com.axity.webapi.soap.api.ws.account.AccountMovement}
   * 
   * @param dto
   * @return
   */
  public static AccountMovement transform(AccountMovementDTO dto) {
    AccountMovement accountMovement = null;
    if (dto != null) {
      accountMovement = new AccountMovement();
      accountMovement.setAccountId(dto.getId());
      accountMovement.setAmount(dto.getAmount());
      accountMovement.setId(dto.getId());
      accountMovement.setMovementTimestamp(XMLGregorianCalendarFactory.transform(dto.getMovementTimestamp()));
      accountMovement.setMovementType(transform(dto.getMovementType()));
    }
    return accountMovement;
  }

  /***
   * Transforms the movement type enumeration from one type to the other.
   * 
   * @param movementType
   * @return
   */
  public static MovementType transform(mx.com.axity.webapi.soap.api.commons.enums.MovementType movementType) {

    Map<mx.com.axity.webapi.soap.api.commons.enums.MovementType, MovementType> map =
        new EnumMap<>(mx.com.axity.webapi.soap.api.commons.enums.MovementType.class);

    map.put(mx.com.axity.webapi.soap.api.commons.enums.MovementType.ADD_BALANCE, MovementType.ADD_BALANCE);
    map.put(mx.com.axity.webapi.soap.api.commons.enums.MovementType.BALANCE_WITHDRAWAL,
        MovementType.BALANCE_WITHDRAWAL);

    return map.get(movementType);
  }

}
