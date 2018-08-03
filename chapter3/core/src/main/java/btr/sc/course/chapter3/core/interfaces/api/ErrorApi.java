package btr.sc.course.chapter3.core.interfaces.api;

import btr.sc.course.chapter3.core.infrastructure.Restful;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorApi
{
  @ExceptionHandler(HystrixBadRequestException.class)
  public ResponseEntity badRequest(final HystrixBadRequestException badRequest)
  {
    val msg = badRequest.getMessage();

    return Restful.badRequest(msg);
  }
}
