package btr.sc.course.chapter2.interfaces.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class ItemApi
{
  @GetMapping
  public String hello()
  {
    return "Hello Item Module";
  }
}
