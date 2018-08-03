package btr.sc.course.chapter3.interfaces.api.v1;

import btr.sc.course.chapter3.application.service.ItemService;
import btr.sc.course.chapter3.core.infrastructure.Restful;
import btr.sc.course.chapter3.interfaces.dto.ItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("items")
public class ItemApi
{
  private final ItemService service;

  @PostMapping
  public ResponseEntity create(@RequestBody final ItemDTO.Create request, final HttpServletRequest r)
  {
    return Restful.created(service.create(request), r);
  }

  @GetMapping("{id}")
  public ResponseEntity show(@PathVariable final String id)
  {
    return Restful.ok(service.getOne(id));
  }

  @PutMapping
  public ResponseEntity update(@RequestBody final ItemDTO.Update request)
  {
    return Restful.completed(service.update(request));
  }
}
