package com.example.loggingcoffee.coffee.order;

import lombok.Data;

/*
 * @author Oksiuta Andrii
 * 26.01.2023
 * 16:57
 */

@Data
public class Order {
  private int number;
  private String name;

  public Order(String name) {
    this.name = name;
  }
}
