package btr.sc.course.chapter1.interfaces.assembler;

import btr.sc.course.chapter1.domain.Item;
import btr.sc.course.chapter1.interfaces.dto.ItemDTO;
import lombok.val;

public interface ItemAssembler
{
  static ItemDTO.Item toDTO(Item domain)
  {
    return new ItemDTO.Item(domain.getName(), domain.getCreateTime(), domain.getUpdateTime());
  }

  static Item toDomain(ItemDTO.Item dto)
  {
    val domain = new Item();
    domain.setName(dto.name);
    domain.setCreateTime(dto.createTime);
    domain.setUpdateTime(dto.updateTime);

    return domain;
  }
}
