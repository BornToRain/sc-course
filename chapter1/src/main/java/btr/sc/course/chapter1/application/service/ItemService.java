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

  public List<ItemDTO.Item> getAll()
  {
    return repository.findAll()
             .map(ItemAssembler::toDTO);
  }

  public Option<ItemDTO.Item> getOne(final String id)
  {
    return repository.findOne(id)
             .map(ItemAssembler::toDTO);
  }

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

  public Option<ItemDTO.Item> update(final ItemDTO.Update request)
  {
    val domain = new Item();
    domain.setId(request.id);
    domain.setName(request.name);
    domain.setUpdateTime(new Date());

    return repository.update(domain)
             .map(ItemAssembler::toDTO);
  }

  public String delete(final String id)
  {
    return repository.delete(id);
  }
}
