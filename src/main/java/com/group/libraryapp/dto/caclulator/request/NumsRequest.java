package com.group.libraryapp.dto.caclulator.request;

import java.util.List;

public class NumsRequest {
  private List<Integer> numbers;

  public List<Integer> getNumbers() {
    return numbers;
  }

  public NumsRequest(List<Integer> numbers) {
    this.numbers = numbers;
  }
  public NumsRequest(){}
}
