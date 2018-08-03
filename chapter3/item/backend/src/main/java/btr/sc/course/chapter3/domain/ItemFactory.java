package btr.sc.course.chapter3.domain;

import btr.sc.course.chapter3.core.domain.DomainError;
import btr.sc.course.chapter3.core.infrastructure.IdWorker;
import btr.sc.course.chapter3.interfaces.dto.ItemDTO;
import io.vavr.API;
import io.vavr.control.Either;
import lombok.val;

import java.util.Date;

public class ItemFactory
{
  private static Either<DomainError, String> validateName(final String name)
  {
    return "ErrorName".equals(name) ? API.Left(new DomainError(0, "项目名错误!")) : API.Right(name);
  }

  public static Either<DomainError, Item> validate(final ItemDTO.Create request)
  {
    val now = new Date();

    return validateName(request.name)
      .map(name -> new Item(IdWorker.getId(), name, 5, now, now));
  }
}
