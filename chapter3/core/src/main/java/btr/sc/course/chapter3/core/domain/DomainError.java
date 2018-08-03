package btr.sc.course.chapter3.core.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class DomainError
{
  public final int    code;
  public final String msg;
}
