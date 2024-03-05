package com.group.libraryapp.dto.fruit.response;

public class FruitPriceResponse {

  private final long salesAmount;
  private final long notSalesAmount;

  public FruitPriceResponse(long salesAmount, long notSalesAmount) {
    this.salesAmount = salesAmount;
    this.notSalesAmount = notSalesAmount;
  }

  public long getSalesAmount() {
    return salesAmount;
  }

  public long getNotSalesAmount() {
    return notSalesAmount;
  }

}
