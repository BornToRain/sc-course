package btr.sc.course.chapter3.infrastructure.service;

import btr.sc.course.chapter3.interfaces.dto.ItemDTO;
import io.vavr.control.Option;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "item", decode404 = true)
public interface ItemService
{
  @PutMapping("/items")
  Option<ItemDTO.Item> update(@RequestBody ItemDTO.Update request);
}
