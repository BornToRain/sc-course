package btr.sc.course.chapter3.interfaces.dto;

import lombok.AllArgsConstructor;

import java.util.Date;

public interface UserDTO
{
  @AllArgsConstructor
  final class Create
  {
    public final String name;
    public final String pwd;
  }

  @AllArgsConstructor
  final class Buy
  {
    public final String  id;
    public final String  itemId;
    public final Integer count;
  }

  @AllArgsConstructor
  final class User
  {
    public final String  id;
    public final String  name;
    public final Integer count;
    public final Date    createTime;
    public final Date    updateTime;
  }
}
