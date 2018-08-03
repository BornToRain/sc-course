package btr.sc.course.chapter3.application.service;

import btr.sc.course.chapter3.core.domain.DomainError;
import btr.sc.course.chapter3.domain.Item;
import btr.sc.course.chapter3.domain.ItemFactory;
import btr.sc.course.chapter3.domain.ItemRepository;
import btr.sc.course.chapter3.interfaces.assembler.ItemAssembler;
import btr.sc.course.chapter3.interfaces.dto.ItemDTO;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService
{
  private final ItemRepository repository;

  public Either<DomainError, String> create(final ItemDTO.Create request)
  {
    return ItemFactory.validate(request)
             .map(d -> repository.save(d).getId());
  }

  public Option<ItemDTO.Item> getOne(final String id)
  {
    return repository.findById(id)
             .map(ItemAssembler::toDTO);
  }

  public Option<Either<DomainError, ItemDTO.Item>> update(final ItemDTO.Update request)
  {
    return repository.findById(request.id)
             .map(d -> d.update(request.count))
             .map(d -> d.map(repository::save)
                          .map(ItemAssembler::toDTO));
  }

}
