package btr.sc.course.chapter3.application.service;

import btr.sc.course.chapter3.core.domain.DomainError;
import btr.sc.course.chapter3.domain.User;
import btr.sc.course.chapter3.domain.UserFactory;
import btr.sc.course.chapter3.domain.UserRepository;
import btr.sc.course.chapter3.infrastructure.service.ItemService;
import btr.sc.course.chapter3.interfaces.dto.ItemDTO;
import btr.sc.course.chapter3.interfaces.dto.UserAssembler;
import btr.sc.course.chapter3.interfaces.dto.UserDTO;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import static io.vavr.API.Right;
import static io.vavr.API.println;

@Service
@AllArgsConstructor
public class UserService
{
  private final UserRepository repository;
  private final ItemService    itemService;

  public Either<DomainError, String> create(final UserDTO.Create request)
  {
    return UserFactory.validate(request)
             .map(d -> repository.save(d).getId());
  }

  public Option<UserDTO.User> getById(final String id)
  {
    return repository.findById(id)
             .map(UserAssembler::toDTO);
  }

  public Option<UserDTO.User> buy(final UserDTO.Buy request)
  {
    return repository.findById(request.id)
             .map(d ->
             {
               val itemRequest = new ItemDTO.Update(request.itemId, request.count);
               itemService.update(itemRequest);
               return UserAssembler.toDTO(repository.save(d.buy(2)));
             });
  }
}
