package btr.sc.course.chapter1.infrastructure;

import btr.sc.course.chapter1.domain.Item;
import btr.sc.course.chapter1.domain.ItemRepository;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import lombok.val;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository
{
  private Map<String, Item> map = HashMap.empty();

  @Override
  public String insert(final Item d)
  {
    val id = IdWorker.getId();
    d.setId(id);
    map    = map.put(id, d);

    return id;
  }

  @Override
  public List<Item> findAll()
  {
    return map.map(t -> t._2).toList();
  }

  @Override
  public Option<Item> findById(final String id)
  {
    return map.get(id);
  }

  @Override
  public Item update(final Item d)
  {
    val id = d.getId();
    return map.get(id)
             .map(data ->
             {
               data.setName(d.getName());
               map.put(id, data);
               return data;
             }).getOrElse(d);
  }

  @Override
  public String delete(final String id)
  {
    map = map.remove(id);
    return id;
  }
}
