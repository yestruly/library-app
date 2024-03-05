package com.group.libraryapp.dto.fruit.response;

import com.group.libraryapp.domain.fruit.Fruit;
import java.time.LocalDate;

public class FruitListResponse {
  private String name;
  private Long price;
  private LocalDate warehousingDate;

  public FruitListResponse(Fruit fruit) {
    this.name = fruit.getName();
    this.price = fruit.getPrice();
    this.warehousingDate = fruit.getWarehousingDate();
  }

  public FruitListResponse() {
  }

  public String getName() {
    return name;
  }

  public Long getPrice() {
    return price;
  }

  public LocalDate getWarehousingDate() {
    return warehousingDate;
  }

}
