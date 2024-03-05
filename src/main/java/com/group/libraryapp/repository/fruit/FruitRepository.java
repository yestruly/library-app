package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitPriceResponse;
import java.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FruitRepository {
  private final JdbcTemplate jdbcTemplate;


  public FruitRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void registerFruit(String name, LocalDate warehousingDate, long price){
    String sql = "INSERT INTO fruit(name, warehousingDate, price) VALUES (?,?,?)";
    jdbcTemplate.update(sql, name,warehousingDate, price);
  }

  public boolean isFruitNotExist(long id){
    String sql = "SELECT * FROM fruit WHERE id = ?";
    return jdbcTemplate.query(sql, (rs, rowNum) ->
        0, id).isEmpty();
  }

  public void updateFruit(long id) {
    String updateSql = "UPDATE fruit SET isSell = ? WHERE id = ?";
    jdbcTemplate.update(updateSql, true, id);
  }

  public FruitPriceResponse getFruitSellPrice(String name){
    String sql =  "SELECT "
        + "SUM(CASE WHEN isSell = true THEN price ELSE 0 END) AS salesAmount, \n"
        + "SUM(CASE WHEN isSell = false THEN price ELSE 0 END) AS notSalesAmount \n"
        + "FROM fruit \n"
        + "WHERE name = ?";


    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      long salesAmount = rs.getLong("salesAmount");
      long notSalesAmount = rs.getLong("notSalesAmount");
      return new FruitPriceResponse(salesAmount, notSalesAmount);
    }, name);

  }
}
