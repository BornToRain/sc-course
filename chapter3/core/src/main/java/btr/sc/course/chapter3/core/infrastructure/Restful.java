package btr.sc.course.chapter3.core.infrastructure;

import btr.sc.course.chapter3.core.domain.DomainError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.function.Function;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;

public interface Restful
{
  String GATEWAY = "http://192.168.1.4:8000";

  static ResponseEntity created(Either<DomainError, String> data, HttpServletRequest r)
  {
    return Match(data).of(
      Case($Right($()), d -> ResponseEntity.created(URI.create(GATEWAY + r.getRequestURI() + "/" + d)).build()),
      Case($Left($()) , Restful::badRequest)
    );
  }

  static <T> ResponseEntity ok(Option<T> data)
  {
    return data
             .map(ResponseEntity::ok)
             .getOrElse(Restful::notFound);
  }

  static <T> ResponseEntity completed(Option<Either<DomainError, T>> data)
  {
    return data
             .map(d -> Match(d).of(
               Case($Right($()), (Function<T, ResponseEntity<T>>) ResponseEntity::ok),
               Case($Left($()) , Restful::badRequest)
             ))
             .getOrElse(Restful::notFound);
  }

  static ResponseEntity noContent()
  {
    return ResponseEntity.noContent().build();
  }

  static ResponseEntity badRequest(DomainError error)
  {
    return ResponseEntity.badRequest().body(error);
  }

  static ResponseEntity badRequest(String s)
  {
    return ResponseEntity.badRequest().body(s);
  }

  static ResponseEntity notFound()
  {
    return ResponseEntity.notFound().build();
  }
}
