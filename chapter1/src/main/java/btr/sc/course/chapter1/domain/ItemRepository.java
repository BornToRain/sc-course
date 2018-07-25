package btr.sc.course.chapter1.domain;


import io.vavr.collection.List;
import io.vavr.control.Option;

public interface ItemRepository
{
  String       insert(Item d);
  List<Item>   findAll();
  Option<Item> findById(String id);
  Item         update(Item d);
  String       delete(String id);
}
