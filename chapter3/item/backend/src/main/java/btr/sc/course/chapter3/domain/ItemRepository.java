package btr.sc.course.chapter3.domain;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String>
{
  Option<Item> findById(String id);
}
