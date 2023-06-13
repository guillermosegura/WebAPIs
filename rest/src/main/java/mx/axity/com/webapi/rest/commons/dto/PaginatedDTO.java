package mx.axity.com.webapi.rest.commons.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The pagination wrapper class.
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 */

public class PaginatedDTO<T> {

  @Schema(description = "List of items")
  private List<T> items;

  @Schema(description = "Page size")
  private int size;

  @Schema(description = "Page offset")
  private int offset;

  @Schema(description = "Total number of records")
  private long records;

  @Schema(description = "Total number of pages")
  private int pages;

  /**
   * Default constructor.
   */
  public PaginatedDTO() {}

  /***
   * Constructor by items, size, offset, records and pages.
   * 
   * @param items
   * @param size
   * @param offset
   * @param records
   * @param pages
   */
  public PaginatedDTO(List<T> items, int size, int offset, long records, int pages) {
    this.items = items;
    this.size = size;
    this.offset = offset;
    this.records = records;
    this.pages = pages;
  }

  /**
   * @return the items
   */
  public List<T> getItems() {
    return items;
  }

  /**
   * @param items the items to set
   */
  public void setItems(List<T> items) {
    this.items = items;
  }

  /**
   * @return the size
   */
  public int getSize() {
    return size;
  }

  /**
   * @param size the size to set
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * @return the offset
   */
  public int getOffset() {
    return offset;
  }

  /**
   * @param offset the offset to set
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }

  /**
   * @return the records
   */
  public long getRecords() {
    return records;
  }

  /**
   * @param records the records to set
   */
  public void setRecords(long records) {
    this.records = records;
  }

  /**
   * @return the pages
   */
  public int getPages() {
    return pages;
  }

  /**
   * @param pages the pages to set
   */
  public void setPages(int pages) {
    this.pages = pages;
  }

}
