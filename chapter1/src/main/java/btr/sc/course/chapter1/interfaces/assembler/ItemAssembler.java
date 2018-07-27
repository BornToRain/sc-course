package btr.sc.course.chapter1.interfaces.assembler;

import btr.sc.course.chapter1.domain.Item;
import btr.sc.course.chapter1.interfaces.dto.ItemDTO;

public interface ItemAssembler
{
  static ItemDTO.Item toDTO(Item domain)
  {
    return new ItemDTO.Item(domain.getName(), domain.getCreateTime(), domain.getUpdateTime());
  }
}
