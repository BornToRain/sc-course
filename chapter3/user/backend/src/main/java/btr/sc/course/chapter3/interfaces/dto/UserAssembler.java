package btr.sc.course.chapter3.interfaces.dto;

import btr.sc.course.chapter3.domain.User;

public interface UserAssembler
{
  static UserDTO.User toDTO(User domain)
  {
    return new UserDTO.User(domain.getId(), domain.getName(), domain.getCount(), domain.getCreateTime(), domain.getUpdateTime());
  }
}
