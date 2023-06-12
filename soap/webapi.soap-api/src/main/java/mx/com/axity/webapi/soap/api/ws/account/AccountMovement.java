
package mx.com.axity.webapi.soap.api.ws.account;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para AccountMovement complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AccountMovement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="movementType" type="{http://axity.com.mx/webapi/soap/api/ws/account}MovementType"/&gt;
 *         &lt;element name="movementTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountMovement", propOrder = {
    "id",
    "accountId",
    "amount",
    "movementType",
    "movementTimestamp"
})
public class AccountMovement {

    protected int id;
    protected int accountId;
    @XmlElement(required = true)
    protected BigDecimal amount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected MovementType movementType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar movementTimestamp;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad accountId.
     * 
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Define el valor de la propiedad accountId.
     * 
     */
    public void setAccountId(int value) {
        this.accountId = value;
    }

    /**
     * Obtiene el valor de la propiedad amount.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Define el valor de la propiedad amount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Obtiene el valor de la propiedad movementType.
     * 
     * @return
     *     possible object is
     *     {@link MovementType }
     *     
     */
    public MovementType getMovementType() {
        return movementType;
    }

    /**
     * Define el valor de la propiedad movementType.
     * 
     * @param value
     *     allowed object is
     *     {@link MovementType }
     *     
     */
    public void setMovementType(MovementType value) {
        this.movementType = value;
    }

    /**
     * Obtiene el valor de la propiedad movementTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMovementTimestamp() {
        return movementTimestamp;
    }

    /**
     * Define el valor de la propiedad movementTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMovementTimestamp(XMLGregorianCalendar value) {
        this.movementTimestamp = value;
    }

}
