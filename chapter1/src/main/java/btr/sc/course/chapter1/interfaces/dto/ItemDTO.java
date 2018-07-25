package btr.sc.course.chapter1.interfaces.dto;

import lombok.AllArgsConstructor;

import java.util.Date;

public interface ItemDTO
{
  @AllArgsConstructor
  final class Create implements ItemDTO
  {
    public final String name;
  }

  @AllArgsConstructor
  final class Update implements ItemDTO
  {
    public final String id;
    public final String name;
  }

  @AllArgsConstructor
  final class Item implements ItemDTO
  {
    public final String name;
    public final Date   createTime;
    public final Date   updateTime;
  }
}
