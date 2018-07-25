package btr.sc.course.chapter1.interfaces.api.v1;

import btr.sc.course.chapter1.application.service.ItemService;
import btr.sc.course.chapter1.interfaces.dto.ItemDTO;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("items")
public class ItemApi
{
  private final ItemService service;

  @PostMapping
  public ResponseEntity create(@RequestBody final ItemDTO.Create request, final HttpServletRequest r)
  {
    val id = service.create(request);

    return ResponseEntity.created(URI.create(r.getRequestURL().append("/").append(id).toString())).build();
  }

  @GetMapping
  public List<ItemDTO.Item> list()
  {
    return service.getAll();
  }

  @GetMapping("{id}")
  public ResponseEntity show(@PathVariable final String id)
  {
    return service.getById(id)
             .map(d -> ResponseEntity.ok()
                         .body(d))
             .getOrElse(ResponseEntity.notFound().build());
  }

  @PutMapping("{id}")
  public ResponseEntity edit(@PathVariable final String id, @RequestBody final ItemDTO.Update request)
  {
    val data = service.update(request);

    return ResponseEntity.ok(data);
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable final String id)
  {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
