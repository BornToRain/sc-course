package btr.sc.course.chapter1.application.service;

import btr.sc.course.chapter1.domain.Item;
import btr.sc.course.chapter1.domain.ItemRepository;
import btr.sc.course.chapter1.infrastructure.IdWorker;
import btr.sc.course.chapter1.interfaces.assembler.ItemAssembler;
import btr.sc.course.chapter1.interfaces.dto.ItemDTO;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ItemService
{
  private final ItemRepository repository;

  public String create(final ItemDTO.Create request)
  {
    val id     = IdWorker.getId();
    val now    = new Date();
    val domain = new Item();
    domain.setId(id);
    domain.setName(request.name);
    domain.setCreateTime(now);
    domain.setUpdateTime(now);

    return repository.insert(domain);
  }

  public List<ItemDTO.Item> getAll()
  {
    return repository.findAll()
             .map(ItemAssembler::toDTO);
  }

  public Option<ItemDTO.Item> getById(final String id)
  {
    return repository.findById(id)
             .map(ItemAssembler::toDTO);
  }

  public ItemDTO.Item update(final ItemDTO.Update request)
  {
    val domain = new Item();
    domain.setId(request.id);
    domain.setName(request.name);

    return ItemAssembler.toDTO(repository.update(domain));
  }

  public String delete(final String id)
  {
    return repository.delete(id);
  }
}
