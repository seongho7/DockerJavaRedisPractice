package com.sh.adsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AdServicePracticeApplication {
  public static void main(String[] args) {
    SpringApplication.run(AdServicePracticeApplication.class, args);
  }
}
