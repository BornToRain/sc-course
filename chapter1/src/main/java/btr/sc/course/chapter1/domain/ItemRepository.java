package btr.sc.course.chapter1.domain;

import io.vavr.collection.List;
import io.vavr.control.Option;

public interface ItemRepository
{
  List<Item>   findAll();
  Option<Item> findOne(String id);
  String       insert(Item data);
  Option<Item> update(Item data);
  String       delete(String id);
}
