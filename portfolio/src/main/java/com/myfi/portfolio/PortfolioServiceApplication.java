package com.myfi.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PortfolioServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(PortfolioServiceApplication.class, args);
  }
}
