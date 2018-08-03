package btr.sc.course.chapter3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User
{
  @Id
  private String  id;
  private String  name;
  private String  pwd;
  private Integer count;
  private Date    createTime;
  private Date    updateTime;

  public User buy(final Integer count)
  {
    this.count += count;
    return this;
  }
}
