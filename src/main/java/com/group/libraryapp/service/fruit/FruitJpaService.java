package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitCountResponse;
import com.group.libraryapp.dto.fruit.response.FruitListResponse;
import com.group.libraryapp.dto.fruit.response.FruitPriceResponse;
import com.group.libraryapp.repository.fruit.FruitJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FruitJpaService {
  private final FruitJpaRepository fruitRepository;

  public FruitJpaService(FruitJpaRepository fruitRepository) {
    this.fruitRepository = fruitRepository;
  }

  @Transactional
  public void registerFruit(FruitRequest request) {
    fruitRepository.save(new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice()));
  }

  @Transactional
  public void updateFruit(FruitUpdateRequest request) {
    Fruit fruit = fruitRepository.findById(request.getId())
        .orElseThrow(IllegalArgumentException::new);

    fruit.updateSellStatus();
  }

  @Transactional
  public FruitPriceResponse getFruitSellPrice(String name) {
    Long salesAmount = fruitRepository.findByNameAndIsSell(name, true)
        .stream().mapToLong(fruits -> fruits.getPrice()).sum();
    Long notSalesAmount = fruitRepository.findByNameAndIsSell(name, false)
        .stream().mapToLong(fruits -> fruits.getPrice()).sum();

    return new FruitPriceResponse(salesAmount, notSalesAmount);
  }

  public FruitCountResponse getFruitCount(String name) {
    Long count = fruitRepository.countByName(name);
    return new FruitCountResponse(count);
  }

  public List<FruitListResponse> getFruitList(String option, Long price) {
    List<Fruit> fruits;
    if ("GTE".equals(option)) {
      fruits = fruitRepository.findAllByPriceGreaterThanEqualAndIsSellIsFalse(price);
    } else if ("LTE".equals(option)) {
      fruits = fruitRepository.findAllByPriceLessThanEqualAndIsSellIsFalse(price);
    } else {
      throw new IllegalArgumentException();
    }
    return fruits.stream()
        .map(FruitListResponse::new)
        .collect(Collectors.toList());
  }
}
