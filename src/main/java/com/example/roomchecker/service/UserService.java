package com.example.roomchecker.service;

import com.example.roomchecker.model.User;
import com.example.roomchecker.repository.RoomRepository;
import com.example.roomchecker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserRepository repository;
  private final RoomRepository roomRepository;

  public boolean canEnter(long userId, long roomId) {
    User user = repository.getById(userId);
    boolean isTrue = false;
    if (user.getId() % roomId == 0 && !user.isInside()) {
      User updatedUser = new User(
          user.getId(),
          user.getName(),
          user.isInside(),
          user.getRoom());
      updatedUser.setRoom(roomRepository.getById(roomId));
      updatedUser.setInside(true);
      repository.save(updatedUser);
      isTrue = true;
    }
    return isTrue;
  }
}
