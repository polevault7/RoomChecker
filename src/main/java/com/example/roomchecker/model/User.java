package com.example.roomchecker.model;

import lombok.Data;

@Data
public class User {
  private long id;
  private String name;
  private boolean isInside;
  private Room room;

  public User(long id, String name, boolean isInside, Room room) {
    this.id = id;
    this.name = name;
    this.isInside = isInside;
    this.room = room;
  }
}
