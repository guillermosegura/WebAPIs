
package mx.com.axity.webapi.soap.api.ws.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PaginatedPerson complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PaginatedPerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="items" type="{http://axity.com.mx/webapi/soap/api/ws/person}PersonList"/&gt;
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="records" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="pages" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaginatedPerson", propOrder = {
    "items",
    "size",
    "offset",
    "records",
    "pages"
})
public class PaginatedPerson {

    @XmlElement(required = true)
    protected PersonList items;
    protected int size;
    protected int offset;
    protected long records;
    protected int pages;

    /**
     * Obtiene el valor de la propiedad items.
     * 
     * @return
     *     possible object is
     *     {@link PersonList }
     *     
     */
    public PersonList getItems() {
        return items;
    }

    /**
     * Define el valor de la propiedad items.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonList }
     *     
     */
    public void setItems(PersonList value) {
        this.items = value;
    }

    /**
     * Obtiene el valor de la propiedad size.
     * 
     */
    public int getSize() {
        return size;
    }

    /**
     * Define el valor de la propiedad size.
     * 
     */
    public void setSize(int value) {
        this.size = value;
    }

    /**
     * Obtiene el valor de la propiedad offset.
     * 
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Define el valor de la propiedad offset.
     * 
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Obtiene el valor de la propiedad records.
     * 
     */
    public long getRecords() {
        return records;
    }

    /**
     * Define el valor de la propiedad records.
     * 
     */
    public void setRecords(long value) {
        this.records = value;
    }

    /**
     * Obtiene el valor de la propiedad pages.
     * 
     */
    public int getPages() {
        return pages;
    }

    /**
     * Define el valor de la propiedad pages.
     * 
     */
    public void setPages(int value) {
        this.pages = value;
    }

}
