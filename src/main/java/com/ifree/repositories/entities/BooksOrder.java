package com.ifree.repositories.entities;

import static com.ifree.constants.Constants.BOOKS_TYPE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue(BOOKS_TYPE)
@EqualsAndHashCode(callSuper = true)
public
class BooksOrder extends Order {

  @Column
  private String author;
  @Column(name = "count_of_pages")
  private int countOfPages;
  @Column(name = "book_year")
  private String bookYear;

}
