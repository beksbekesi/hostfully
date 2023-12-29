package com.hostfully.interview.controller;

import static com.hostfully.interview.utils.DateRangeValidator.validateDates;

import com.hostfully.interview.domain.dto.block.BlockDto;
import com.hostfully.interview.service.BlockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/blocks")
public class BlockController {

  private final BlockService blockService;

  @PostMapping
  public ResponseEntity<BlockDto> createBlock(@RequestBody @Valid BlockDto blockDto) {
    validateDates(blockDto.startDate(), blockDto.endDate());
    return ResponseEntity.status(HttpStatus.CREATED).body(blockService.createBlock(blockDto));
  }

  @PutMapping("/{blockId}")
  public ResponseEntity<BlockDto> updateBlock(
      @RequestBody @Valid BlockDto blockDto, @PathVariable String blockId) {
    validateDates(blockDto.startDate(), blockDto.endDate());
    return ResponseEntity.status(HttpStatus.OK).body(blockService.updateBlock(blockDto, blockId));
  }

  @DeleteMapping("/{blockId}")
  public ResponseEntity<BlockDto> deleteBlock(@PathVariable String blockId) {
    blockService.deleteBlock(blockId);
    return ResponseEntity.noContent().build();
  }
}
