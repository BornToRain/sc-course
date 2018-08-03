package btr.sc.course.chapter3.domain;

import btr.sc.course.chapter3.core.domain.DomainError;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

import static io.vavr.API.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item
{
  @Id
  private String  id;
  private String  name;
  private Integer count;
  private Date    createTime;
  private Date    updateTime;

  public Either<DomainError, Item> update(final Integer count)
  {
    return Match(count).of(
      Case($(d -> d < 1)         , d -> Either.left(new DomainError(0, "购买数量不能小于1"))),
      Case($(d -> d > this.count), d -> Either.left(new DomainError(1, "购买数量大于存货"))),
      Case($()                   , d ->
      {
        this.count = this.count - count;
        return Either.right(this);
      })
    );
  }
}
