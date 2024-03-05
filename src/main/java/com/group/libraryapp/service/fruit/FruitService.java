package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitPriceResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.stereotype.Service;

@Service
public class FruitService {
  private final FruitRepository fruitRepository;

  public FruitService(FruitRepository fruitRepository) {
    this.fruitRepository = fruitRepository;
  }

  public void registerFruit(FruitRequest request) {
   fruitRepository.registerFruit(request.getName(), request.getWarehousingDate(), request.getPrice());

  }

  public void updateFruit(FruitUpdateRequest request) {
    if(fruitRepository.isFruitNotExist(request.getId())){
      throw new IllegalArgumentException();
    }
    fruitRepository.updateFruit(request.getId());
  }

  public FruitPriceResponse getFruitSellPrice(String name) {
    return fruitRepository.getFruitSellPrice(name);
  }
}
