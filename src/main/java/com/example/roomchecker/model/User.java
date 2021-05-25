package com.example.roomchecker.model;

import java.util.Objects;


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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isInside() {
        return isInside;
    }

    public Room getRoom() {
        return room;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && isInside == user.isInside && Objects.equals(name, user.name) && Objects.equals(room, user.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isInside, room);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isInside=" + isInside +
                ", room=" + room +
                '}';
    }
}
