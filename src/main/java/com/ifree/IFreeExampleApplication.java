package com.ifree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaRepositories(basePackages = "com.ifree.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.ifree.repositories.entities")
@SpringBootApplication
public class IFreeExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(IFreeExampleApplication.class, args);
  }
}