
package mx.com.axity.webapi.soap.api.ws.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResponseWrapperPaginatedPerson complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseWrapperPaginatedPerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="header" type="{http://axity.com.mx/webapi/soap/api/ws/person}Header"/&gt;
 *         &lt;element name="body" type="{http://axity.com.mx/webapi/soap/api/ws/person}PaginatedPerson"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseWrapperPaginatedPerson", propOrder = {
    "header",
    "body"
})
public class ResponseWrapperPaginatedPerson {

    @XmlElement(required = true)
    protected Header header;
    @XmlElement(required = true)
    protected PaginatedPerson body;

    /**
     * Obtiene el valor de la propiedad header.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Define el valor de la propiedad header.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Obtiene el valor de la propiedad body.
     * 
     * @return
     *     possible object is
     *     {@link PaginatedPerson }
     *     
     */
    public PaginatedPerson getBody() {
        return body;
    }

    /**
     * Define el valor de la propiedad body.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginatedPerson }
     *     
     */
    public void setBody(PaginatedPerson value) {
        this.body = value;
    }

}
