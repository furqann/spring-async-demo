package com.furqan.async;

import java.time.Duration;
import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
public class AsyncDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(AsyncDemoApplication.class, args);
  }

  @Bean
  @Lazy
  public RestTemplate restTemplate(final RestTemplateBuilder builder) {
    System.out.println("--------------------- Generating the RestTemplate bean -----------------------");
    builder.setConnectTimeout(Duration.ofMillis(2000));
    builder.setReadTimeout(Duration.ofMillis(2000));
    return builder.build();
  }

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(4);
    executor.setMaxPoolSize(Integer.MAX_VALUE);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("ApiLookup-");
    executor.initialize();
    return executor;
  }

}
