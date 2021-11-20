package com.example.roomchecker.controller;

import com.example.roomchecker.model.User;
import com.example.roomchecker.repository.UserRepository;
import com.example.roomchecker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserRepository repository;
  private final UserService service;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    HttpHeaders header = new HttpHeaders();
    header.add("desc", "All Users: ");
    return ResponseEntity.status(HttpStatus.OK).headers(header).body(repository.getAll());
  }

  @GetMapping("/{userId}")
  public User getById(@PathVariable long userId) {
    return repository.getById(userId);
  }

  @PostMapping
  public User save(@RequestBody User item) {
    return repository.save(item);
  }

  @GetMapping("/check")
  public ResponseEntity<Boolean> canEnter(
      @RequestParam long userId,
      @RequestParam long roomId) {
    HttpHeaders header = new HttpHeaders();
    header.add("desc", "Can enter: ");
    boolean canEnter = service.canEnter(userId, roomId);
    log.info("Проверяем возможность пользователя {} войти в комнату...", userId);
    if (canEnter) {
      log.info("Пользователь {} может войти в комнату с номером {}", userId, roomId);
      return ResponseEntity.status(HttpStatus.OK).headers(header).body(canEnter);
    }
    log.info("У пользователя {} нет права войти в комнату под номером {}", userId, roomId);
    log.info("Причины по которой пользователь {} не может войти в комнату номер {}", userId, roomId);
    log.info("Id пользователя {} не делится нацело на номер комнаты {}", userId, roomId);
    log.info("Пользователь {} должен выйти из другой комнаты, чтобы войти в эту комнату номер {}", userId, roomId);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(header).body(canEnter);
  }
}
