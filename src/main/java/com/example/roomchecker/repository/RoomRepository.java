package com.example.roomchecker.repository;

import com.example.roomchecker.mapper.RoomRowMapper;
import com.example.roomchecker.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RoomRepository {
    private final NamedParameterJdbcTemplate template;
    private final RoomRowMapper rowMapper = new RoomRowMapper();

    public List<Room> getAll() {
        return template.query("select id, room_name from rooms",
                rowMapper
        );
    }

    public Room getById(long id) {
        return template.queryForObject("select id, room_name from rooms where id = :id",
                Map.of("id", id),
                rowMapper
        );
    }
}
