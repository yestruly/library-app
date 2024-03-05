package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FruitJpaRepository extends JpaRepository<Fruit, Long> {
  Optional<Fruit> findById(Long id);
  List<Fruit>  findByNameAndIsSell(String name, boolean isSell);
  Long countByName(String name);

  List<Fruit> findAllByPriceGreaterThanEqualAndIsSellIsFalse(Long price);
  List<Fruit> findAllByPriceLessThanEqualAndIsSellIsFalse(Long price);
}
