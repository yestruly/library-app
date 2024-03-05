package com.group.libraryapp.dto.caclulator.response;

import com.group.libraryapp.dto.caclulator.request.DateRequest;
public class DateResponse {
  private String dayOfWeek;

  public String getDayOfWeek() {
    return dayOfWeek.substring(0,3);
  }

  public DateResponse(DateRequest request) {
    this.dayOfWeek = request.getDate().getDayOfWeek().toString();
  }
}
