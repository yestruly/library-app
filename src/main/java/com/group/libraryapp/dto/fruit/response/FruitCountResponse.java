package com.group.libraryapp.dto.fruit.response;

public class FruitCountResponse {

  public Long getCount() {
    return count;
  }

  private final Long count;


  public FruitCountResponse(Long count) {
    this.count = count;
  }
}
