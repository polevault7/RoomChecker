package com.example.roomchecker.controller;

import com.example.roomchecker.model.Room;
import com.example.roomchecker.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {
  private final RoomRepository repository;

  @GetMapping
  public List<Room> getAll() {
    return repository.getAll();
  }

  @GetMapping("{roomId}")
  public Room getById(@PathVariable long roomId) {
    return repository.getById(roomId);
  }
}

