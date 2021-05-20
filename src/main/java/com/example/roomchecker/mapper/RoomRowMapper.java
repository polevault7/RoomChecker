package com.example.roomchecker.mapper;

import com.example.roomchecker.model.Room;
import com.example.roomchecker.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new Room(
                rs.getLong("id"),
                rs.getString("room_name")
        );
    }
}
