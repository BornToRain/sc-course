package btr.sc.course.chapter1.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Item
{
  private String id;
  private String name;
  private Date   createTime;
  private Date   updateTime;
}
