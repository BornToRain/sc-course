package btr.sc.course.chapter3;

import btr.sc.course.chapter3.core.infrastructure.FeignConfiguration;
import btr.sc.course.chapter3.core.infrastructure.ServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@ImportAutoConfiguration(value = {ServerConfiguration.class, FeignConfiguration.class})
public class UserApp
{
  public static void main(String[] args)
  {
    SpringApplication.run(UserApp.class, args);
  }
}
