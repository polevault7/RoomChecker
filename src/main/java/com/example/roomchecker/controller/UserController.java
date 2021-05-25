package com.example.roomchecker.controller;

import com.example.roomchecker.model.User;
import com.example.roomchecker.repository.UserRepository;
import com.example.roomchecker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;
    private final UserService service;
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UserController.class));

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
        if (canEnter) {
            return ResponseEntity.status(HttpStatus.OK).headers(header).body(canEnter);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(header).body(canEnter);
    }
}
