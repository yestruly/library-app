package com.group.libraryapp.controller.calculator;


import com.group.libraryapp.dto.caclulator.request.CalculatorRequest;
import com.group.libraryapp.dto.caclulator.request.DateRequest;
import com.group.libraryapp.dto.caclulator.request.NumsRequest;
import com.group.libraryapp.dto.caclulator.response.CalculatorResponse;
import com.group.libraryapp.dto.caclulator.response.DateResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")//해당 클래스를 api의 진입 지점으로 만들어줌
public class CalculatorController {
//  @GetMapping("/add")
//  public int addTwoNumbers(CalculatorAddRequest request){
//    return request.getNum1()+ request.getNum2();
//  }
//
//  @PostMapping("/multiply")
//  public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
//    return request.getNum1()*request.getNum2();
//  }

  @GetMapping("/calc")
  public CalculatorResponse calculator(@RequestBody CalculatorRequest request){
    return new CalculatorResponse(request);
  }

  @GetMapping("/day-of-the-week")
  public DateResponse calculateDay(@RequestBody DateRequest request){
    return new DateResponse(request);
  }

  @PostMapping("/sum")
  public Integer addNumbers(@RequestBody NumsRequest request){
    int answer = 0;

    for(int item : request.getNumbers()) {
      answer+=item;
    }
    return answer;
  }

}
