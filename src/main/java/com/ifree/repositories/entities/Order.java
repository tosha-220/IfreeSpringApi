package com.ifree.repositories.entities;

import static com.ifree.constants.Constants.BOOKS_TYPE;
import static com.ifree.constants.Constants.GAMES_TYPE;
import static com.ifree.constants.Constants.TYPE_FIELD;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "orders")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    property = TYPE_FIELD)
@JsonSubTypes({
    @JsonSubTypes.Type(value = BooksOrder.class, name = BOOKS_TYPE),
    @JsonSubTypes.Type(value = GamesOrder.class, name = GAMES_TYPE)
})
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = TYPE_FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonInclude(Include.NON_NULL)
public abstract class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  @JsonIgnore
  private Long id;
  @Column
  private double price;
  @Column
  private String title;
  @Column
  private String description;
  @Column(insertable = false, updatable = false)
  @Enumerated(EnumType.STRING)
  private OrderType type;
}
