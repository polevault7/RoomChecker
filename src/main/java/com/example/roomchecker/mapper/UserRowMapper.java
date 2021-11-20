package com.example.roomchecker.mapper;

import com.example.roomchecker.model.Room;
import com.example.roomchecker.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserRowMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new User(
        rs.getLong("user_id"),
        rs.getString("user_name"),
        rs.getBoolean("isInside"),
        new Room(
            rs.getLong("room_id"),
            rs.getString("room_name")
        )
    );
  }
}
