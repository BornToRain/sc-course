package btr.sc.course.chapter3;

import btr.sc.course.chapter3.core.infrastructure.ServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(value = ServerConfiguration.class)
public class ItemApp
{
  public static void main(String[] args)
  {
    SpringApplication.run(ItemApp.class, args);
  }
}
