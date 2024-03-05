package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitCountResponse;
import com.group.libraryapp.dto.fruit.response.FruitListResponse;
import com.group.libraryapp.dto.fruit.response.FruitPriceResponse;
import com.group.libraryapp.service.fruit.FruitJpaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fruit")
public class FruitController {

  private final FruitJpaService fruitService;

  public FruitController(FruitJpaService fruitService) {
    this.fruitService = fruitService;
  }


  @PostMapping("")
  public void registerFruit(@RequestBody FruitRequest request) {
   fruitService.registerFruit(request);
  }

  @PutMapping("")
  public void updateFruit(@RequestBody FruitUpdateRequest request) {
    fruitService.updateFruit(request);
  }

  @GetMapping("/stat")
  public FruitPriceResponse getFruitSellPrice(@RequestParam String name) {
     return fruitService.getFruitSellPrice(name);
  }

  @GetMapping("/count")
  public FruitCountResponse getFruitCount(@RequestParam String name){
    return fruitService.getFruitCount(name);
  }

  @GetMapping("/list")
  public ResponseEntity<List<FruitListResponse>> getFruitList(@RequestParam String option, @RequestParam Long price){
    List<FruitListResponse> fruits = fruitService.getFruitList(option, price);
    return ResponseEntity.ok(fruits);

  }
}
