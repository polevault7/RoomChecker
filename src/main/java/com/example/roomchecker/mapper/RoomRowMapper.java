package com.example.roomchecker.mapper;

import com.example.roomchecker.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class RoomRowMapper implements RowMapper<Room> {
  @Override
  public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Room(
        rs.getLong("id"),
        rs.getString("room_name")
    );
  }
}
