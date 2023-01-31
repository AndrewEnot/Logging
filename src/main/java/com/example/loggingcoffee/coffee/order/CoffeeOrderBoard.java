package com.example.loggingcoffee.coffee.order;

import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

/*
 * @author Oksiuta Andrii
 * 26.01.2023
 * 16:57
 */
@Service
@Data
@Slf4j
public class CoffeeOrderBoard {

  private int orderNumber;
  private final List<Order> orders;

  //method adds new order to the list of orders
  public void add(Order order) {
    log.info("method`s name: add");
    if (order != null) {
      order.setNumber(++orderNumber);
      orders.add(order);
      log.info("{}#{} {}", "Order", order.getNumber(), "is received!");
    }
  }

  //method deletes oldest order
  public void deliver() {
    log.info("method`s name: deliver in natural order");
    try {
      if (!orders.isEmpty()) {
        log.info("{}#{} {}", "Order", orders.get(0).getNumber(), "is fulfilled!");
        orders.remove(0);
      } else {
        throw new MyException("list of orders isEmpty!!!");
      }
    } catch (MyException exception) {
      log.error("Exception", exception);
    }
  }

  //method deletes order by the number
  public void deliver(int number) {
    log.info("method`s name: deliver by number");
    try {
    if (orders.stream()
        .map(Order::getNumber)
        .noneMatch(integer -> integer.equals(number))) {
      throw new MyException("there is no order with such ID: " + number);
    }
    for (int i = 0; i < orders.size(); i++) {
        if (number == orders.get(i).getNumber()) {
          log.info("{}#{} {}", "Order", orders.get(i).getNumber(), "is fulfilled out of sequence!");
          orders.remove(i);
        }
      }
    } catch (MyException exception) {
      log.error("Exception", exception);
    }
  }


  //method prints to console list of orders in order of making
  public void draw() {
    log.info("method`s name: draw");
    for (Order order : orders) {
      MDC.put("number", String.valueOf(order.getNumber()));
      MDC.put("name", order.getName());
      log.info("order in queue!!!");
    }
  }
}
