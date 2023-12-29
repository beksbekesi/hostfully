package com.hostfully.interview.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hostfully.interview.domain.dto.block.BlockDto;
import com.hostfully.interview.mapper.BlockMapper;
import com.hostfully.interview.repository.BlockRepository;
import com.hostfully.interview.repository.PropertyRepository;
import com.hostfully.interview.repository.entity.Block;
import com.hostfully.interview.repository.entity.Property;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BlockServiceTest {

  private static final String PROPERTY_ID = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";
  @Mock private BlockRepository blockRepository;

  @Mock private PropertyRepository propertyRepository;

  @Mock private BlockMapper blockMapper;

  @InjectMocks private BlockService blockService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createBlockWorksWithValidData() {
    LocalDate startDate = LocalDate.parse("2024-01-01");
    BlockDto blockDto = new BlockDto("id", startDate, startDate.plusDays(5), "reason", PROPERTY_ID);
    Property property = new Property();
    property.setId(PROPERTY_ID);
    Block block = new Block();

    when(propertyRepository.findById(anyString())).thenReturn(Optional.of(property));
    when(blockMapper.toEntity(any(), any())).thenReturn(block);
    when(blockRepository.save(any())).thenReturn(block);
    when(blockMapper.toDto(any())).thenReturn(blockDto);

    BlockDto result = blockService.createBlock(blockDto);

    verify(propertyRepository, times(1)).findById(anyString());
    verify(blockMapper, times(1)).toEntity(any(), any());
    verify(blockRepository, times(1)).save(any());
    verify(blockMapper, times(1)).toDto(any());
    assertEquals("id", result.id());
    assertEquals(startDate, result.startDate());
    assertEquals(startDate.plusDays(5), result.endDate());
  }

  @Test
  void updateBlockWorksWithValidData() {
    String blockId = "blockId";
    LocalDate startDate = LocalDate.parse("2024-01-01");
    BlockDto blockDto =
        new BlockDto(blockId, startDate, startDate.plusDays(5), "reason", PROPERTY_ID);
    Property property = new Property();
    property.setId(PROPERTY_ID);
    Block block = new Block();

    when(blockRepository.findById(blockId)).thenReturn(Optional.of(block));
    when(blockRepository.save(any())).thenReturn(block);
    when(blockMapper.toDto(any())).thenReturn(blockDto);

    BlockDto result = blockService.updateBlock(blockDto, blockId);

    verify(blockRepository, times(1)).findById(blockId);
    verify(blockRepository, times(1)).save(any());
    verify(blockMapper, times(1)).toDto(any());
    assertEquals(blockId, result.id());
    assertEquals(startDate, result.startDate());
    assertEquals(startDate.plusDays(5), result.endDate());
  }

  @Test
  void deleteBlockWorksWithValidData() {
    String blockId = "123";
    Block block = new Block();

    when(blockRepository.findById(blockId)).thenReturn(Optional.of(block));

    assertDoesNotThrow(() -> blockService.deleteBlock(blockId));

    verify(blockRepository, times(1)).findById(blockId);
    verify(blockRepository, times(1)).deleteById(blockId);
  }
}
