
package mx.com.axity.webapi.soap.api.ws.account;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MovementType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="MovementType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AddBalance"/&gt;
 *     &lt;enumeration value="BalanceWithdrawal"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MovementType")
@XmlEnum
public enum MovementType {

    @XmlEnumValue("AddBalance")
    ADD_BALANCE("AddBalance"),
    @XmlEnumValue("BalanceWithdrawal")
    BALANCE_WITHDRAWAL("BalanceWithdrawal");
    private final String value;

    MovementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MovementType fromValue(String v) {
        for (MovementType c: MovementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
