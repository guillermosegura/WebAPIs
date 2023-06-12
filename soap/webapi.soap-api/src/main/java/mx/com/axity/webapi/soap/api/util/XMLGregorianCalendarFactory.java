package mx.com.axity.webapi.soap.api.util;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Factory class for javax.xml.datatype.XMLGregorianCalendar
 * 
 * @author guillermo.segura@axity.com
 *
 */
public class XMLGregorianCalendarFactory {

  /**
   * Transforms a {@link java.util.Date} into a {@link javax.xml.datatype.XMLGregorianCalendar}
   * 
   * @param date
   * @return
   */
  public static XMLGregorianCalendar transform(Date date) {
    XMLGregorianCalendar xmlGregorianCalendar = null;
    if (date != null) {
      // Create a GregorianCalendar instance and set it to the date
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(date);
      try {
        // Get the DatatypeFactory instance
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        // Convert the GregorianCalendar to XMLGregorianCalendar
        xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

      } catch (DatatypeConfigurationException e) {
        // TODO log the error
      }
    }

    return xmlGregorianCalendar;
  }
}
