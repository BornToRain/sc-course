package btr.sc.course.chapter3.interfaces.api.v1;

import btr.sc.course.chapter3.application.service.UserService;
import btr.sc.course.chapter3.core.infrastructure.Restful;
import btr.sc.course.chapter3.interfaces.dto.UserDTO;
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
@RequestMapping("users")
public class UserApi
{
  private final UserService service;

  @PostMapping
  public ResponseEntity create(@RequestBody final UserDTO.Create request, final HttpServletRequest r)
  {
    return Restful.created(service.create(request), r);
  }

  @GetMapping("{id}")
  public ResponseEntity show(@PathVariable final String id)
  {
    return Restful.ok(service.getById(id));
  }

  @PutMapping("buy")
  public ResponseEntity buy(@RequestBody final UserDTO.Buy request)
  {
    return Restful.ok(service.buy(request));
  }
}
