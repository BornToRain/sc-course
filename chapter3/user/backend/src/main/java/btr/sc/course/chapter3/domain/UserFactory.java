package btr.sc.course.chapter3.domain;


import btr.sc.course.chapter3.core.domain.DomainError;
import btr.sc.course.chapter3.core.infrastructure.IdWorker;
import btr.sc.course.chapter3.interfaces.dto.UserDTO;
import io.vavr.API;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.val;

import java.util.ArrayList;
import java.util.Date;

import static io.vavr.API.*;
import static io.vavr.Predicates.isNull;


public class UserFactory
{
  private static Either<DomainError, String> validateName(final String name)
  {
    return "ErrorName".equals(name) ? API.Left(new DomainError(0, "姓名错误!")) : API.Right(name);
  }
  private static Either<DomainError, String> validatePwd(final String pwd)
  {
    return Match(pwd).of(
      Case($(isNull()),() -> API.Left(new DomainError(1, "密码为空!"))),
      Case($()        ,d -> d.length() < 6 ? API.Left(new DomainError(2, "密码长度不足六位!")) : API.Right(d))
    );
  }
  public static Either<DomainError, User> validate(final UserDTO.Create request)
  {
    val now = new Date();

    return validateName(request.name)
             .flatMap(name -> validatePwd(request.pwd)
                                .map(pwd -> new User(IdWorker.getId(), name, pwd, 0, now, now)));
  }

  private static Validation<DomainError, String> vn(final String name)
  {
    return "ErrorName".equals(name) ? API.Invalid(new DomainError(0, "姓名错误!")) : API.Valid(name);
  }
  private static Validation<DomainError, String> vp(final String pwd)
  {
    return Match(pwd).of(
      Case($(isNull()),() -> API.Invalid(new DomainError(1, "密码为空!"))),
      Case($()        ,d -> d.length() < 6 ? API.Invalid(new DomainError(2, "密码长度不足六位!")) : API.Valid(d))
    );
  }
  public static Validation<Seq<DomainError>, User> v(final UserDTO.Create request)
  {
    val now = new Date();

    return Validation.combine(vn(request.name), vp((request.pwd)))
             .ap((name, pwd) -> new User(IdWorker.getId(), name, pwd, 0, now, now));
  }

}
