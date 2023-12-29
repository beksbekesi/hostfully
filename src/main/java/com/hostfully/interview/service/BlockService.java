package com.hostfully.interview.service;

import com.hostfully.interview.domain.dto.block.BlockDto;
import com.hostfully.interview.domain.dto.exception.InvalidDataException;
import com.hostfully.interview.mapper.BlockMapper;
import com.hostfully.interview.repository.BlockRepository;
import com.hostfully.interview.repository.PropertyRepository;
import com.hostfully.interview.repository.entity.Block;
import com.hostfully.interview.repository.entity.Property;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockService {

  private final BlockRepository blockRepository;
  private final PropertyRepository propertyRepository;
  private final BlockMapper blockMapper;

  public BlockDto createBlock(BlockDto blockDto) {
    log.info("Creating block : {}", blockDto);
    Property property =
        propertyRepository
            .findById(blockDto.propertyId())
            .orElseThrow(() -> new InvalidDataException("Property with ID not found: " + blockDto.propertyId()));

    Block entity = blockMapper.toEntity(blockDto, property);
    Block savedBlock = blockRepository.save(entity);
    log.info("Block saved : {}", savedBlock);

    return blockMapper.toDto(savedBlock);
  }

  public BlockDto updateBlock(BlockDto blockDto, String blockId) {
    log.info("Updating block : {}, {}", blockDto, blockId);
    Block block =
        blockRepository
            .findById(blockId)
            .orElseThrow(() -> new InvalidDataException("Block with ID not found: " + blockId));

    block.setStartDate(blockDto.startDate());
    block.setEndDate(blockDto.endDate());
    block.setReason(blockDto.reason());
    Block updatedBlock = blockRepository.save(block);

    log.info("Block updated : {}", updatedBlock);
    return blockMapper.toDto(updatedBlock);
  }

  public void deleteBlock(String id) {
    log.info("Deleting block : {}", id);
    blockRepository
        .findById(id)
        .orElseThrow(() -> new InvalidDataException("Block with ID not found: " + id));

    blockRepository.deleteById(id);
  }

  public boolean checkBlockForDatesAndProperty(
      LocalDate startDate, LocalDate endDate, String propertyId) {
    return blockRepository
        .findBlocksBetweenDatesForProperty(startDate, endDate, propertyId)
        .isEmpty();
  }
}
