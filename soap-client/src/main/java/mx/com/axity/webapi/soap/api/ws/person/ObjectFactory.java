
package mx.com.axity.webapi.soap.api.ws.person;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.axity.webapi.soap.api.ws.person package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.axity.webapi.soap.api.ws.person
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPersonRequest }
     * 
     */
    public GetPersonRequest createGetPersonRequest() {
        return new GetPersonRequest();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link Header }
     * 
     */
    public Header createHeader() {
        return new Header();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link GetPersonsRequest }
     * 
     */
    public GetPersonsRequest createGetPersonsRequest() {
        return new GetPersonsRequest();
    }

    /**
     * Create an instance of {@link GetPersonsResponse }
     * 
     */
    public GetPersonsResponse createGetPersonsResponse() {
        return new GetPersonsResponse();
    }

    /**
     * Create an instance of {@link PaginatedPerson }
     * 
     */
    public PaginatedPerson createPaginatedPerson() {
        return new PaginatedPerson();
    }

    /**
     * Create an instance of {@link CreatePersonRequest }
     * 
     */
    public CreatePersonRequest createCreatePersonRequest() {
        return new CreatePersonRequest();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link UpdatePersonRequest }
     * 
     */
    public UpdatePersonRequest createUpdatePersonRequest() {
        return new UpdatePersonRequest();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link DeletePersonRequest }
     * 
     */
    public DeletePersonRequest createDeletePersonRequest() {
        return new DeletePersonRequest();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link PersonList }
     * 
     */
    public PersonList createPersonList() {
        return new PersonList();
    }

}
