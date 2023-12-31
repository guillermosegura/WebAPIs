package mx.com.axity.webapi.soap.api.commons.base;

import java.io.Serializable;
import java.util.List;

/**
 * The pagination wrapper class.
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 */
public class PaginatedDTO<T> implements Serializable
{
  private static final long serialVersionUID = -2472982720666273676L;
  private List<T> items;
  private int size;
  private int offset;
  private long records;
  private int pages;

  /**
   * Default constructor.
   */
  public PaginatedDTO()
  {
  }

  /***
   * Constructor by items, size, offset, records and pages.
   * 
   * @param items
   * @param size
   * @param offset
   * @param records
   * @param pages
   */
  public PaginatedDTO( List<T> items, int size, int offset, long records, int pages )
  {
    this.items = items;
    this.size = size;
    this.offset = offset;
    this.records = records;
    this.pages = pages;
  }

  /**
   * @return the items
   */
  public List<T> getItems()
  {
    return items;
  }

  /**
   * @param items the items to set
   */
  public void setItems( List<T> items )
  {
    this.items = items;
  }

  /**
   * @return the size
   */
  public int getSize()
  {
    return size;
  }

  /**
   * @param size the size to set
   */
  public void setSize( int size )
  {
    this.size = size;
  }

  /**
   * @return the offset
   */
  public int getOffset()
  {
    return offset;
  }

  /**
   * @param offset the offset to set
   */
  public void setOffset( int offset )
  {
    this.offset = offset;
  }

  /**
   * @return the records
   */
  public long getRecords()
  {
    return records;
  }

  /**
   * @param records the records to set
   */
  public void setRecords( long records )
  {
    this.records = records;
  }

  /**
   * @return the pages
   */
  public int getPages()
  {
    return pages;
  }

  /**
   * @param pages the pages to set
   */
  public void setPages( int pages )
  {
    this.pages = pages;
  }

}
