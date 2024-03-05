package com.group.libraryapp.domain.fruit;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fruit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column
  private String name;

  @Column(name = "warehousing_date")
  private LocalDate warehousingDate;

  @Column
  private Long price;

  @Column(name = "is_sell")
  private boolean isSell;

  public Fruit(String name, LocalDate warehousingDate, Long price) {
    this.name = name;
    this.warehousingDate = warehousingDate;
    this.price = price;
    this.isSell = false;
  }

  protected Fruit(){}

  public void updateSellStatus() {
    this.isSell = true;
  }

  public Long getPrice(){
    return this.price;
  }

  public String getName(){return this.name;}
  public LocalDate getWarehousingDate(){return this.warehousingDate;}
}
