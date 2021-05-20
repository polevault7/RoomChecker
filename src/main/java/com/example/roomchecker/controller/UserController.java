package com.example.roomchecker.controller;

import com.example.roomchecker.model.User;
import com.example.roomchecker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        return repository.getAll();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable long userId) {
        return repository.getById(userId);
    }
}
