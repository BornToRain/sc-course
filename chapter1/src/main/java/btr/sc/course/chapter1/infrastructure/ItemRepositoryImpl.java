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
  public List<Item> findAll()
  {
    return map.values().toList();
  }

  @Override
  public Option<Item> findOne(final String id)
  {
    return map.get(id);
  }

  @Override
  public String insert(final Item data)
  {
    val id = data.getId();
    map    = map.put(id, data);

    return id;
  }

  @Override
  public Option<Item> update(final Item data)
  {
    val id = data.getId();

    return map.get(id).map(d ->
    {
      d.setName(data.getName());
      map.put(id, d);

      return d;
    });
  }

  @Override
  public String delete(final String id)
  {
    map = map.remove(id);

    return id;
  }
}
