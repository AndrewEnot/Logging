package com.example.loggingcoffee;

import com.example.loggingcoffee.coffee.order.CoffeeOrderBoard;
import com.example.loggingcoffee.coffee.order.Order;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Data
public class LoggingCoffeeApplication {
  private final CoffeeOrderBoard coffeeOrderBoard;

  public static void main(String[] args) {
    SpringApplication.run(LoggingCoffeeApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void start() {
    coffeeOrderBoard.add(new Order("Andrew"));
    coffeeOrderBoard.add(new Order("John"));
    coffeeOrderBoard.add(new Order("Mary"));
    coffeeOrderBoard.add(new Order("Steward"));

    coffeeOrderBoard.deliver();

    coffeeOrderBoard.deliver(2);

    coffeeOrderBoard.deliver(6);

    coffeeOrderBoard.draw();
  }
}
