package btr.sc.course.chapter3.interfaces.assembler;

import btr.sc.course.chapter3.domain.Item;
import btr.sc.course.chapter3.interfaces.dto.ItemDTO;

public interface ItemAssembler
{
  static ItemDTO.Item toDTO(Item domain)
  {
    return new ItemDTO.Item(domain.getId(), domain.getName(), domain.getCount());
  }
}
