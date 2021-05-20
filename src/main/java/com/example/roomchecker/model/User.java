package com.example.roomchecker.model;

import lombok.Value;

@Value
public class User {
    long id;
    String name;
    boolean isInside;
    Room room;
}
