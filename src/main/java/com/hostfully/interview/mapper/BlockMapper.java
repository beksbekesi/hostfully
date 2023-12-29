package com.hostfully.interview.mapper;

import com.hostfully.interview.domain.dto.block.BlockDto;
import com.hostfully.interview.repository.entity.Block;
import com.hostfully.interview.repository.entity.Property;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BlockMapper {

  @Mapping(target = "id", ignore = true)
  public abstract Block toEntity(BlockDto blockDto, @Context Property property);

  @Mapping(source = "property.id", target = "propertyId")
  public abstract BlockDto toDto(Block block);

  @BeforeMapping
  protected void doBeforeMapping(
      BlockDto blockDto, @MappingTarget Block entity, @Context Property property) {
    entity.setProperty(property);
    property.getBlocks().add(entity);
  }
}
