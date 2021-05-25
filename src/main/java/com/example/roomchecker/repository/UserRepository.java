package com.example.roomchecker.repository;

import com.example.roomchecker.mapper.UserRowMapper;
import com.example.roomchecker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final NamedParameterJdbcTemplate template;
    private final UserRowMapper rowMapper = new UserRowMapper();

    public List<User> getAll() {
        return template.query("select  users.user_id as users_id, users.user_name," +
                        " users.isInside,users.room_id, r.id, r.room_name from users" +
                        " inner join rooms r on r.id = users.room_id",
                rowMapper
        );
    }

    public User getById(long id) {
        return template.queryForObject("select user_id, user_name, isInside, room_id, r.id, room_name" +
                        " from users inner join rooms r on r.id = users.room_id where user_id = :user_id",
                Map.of("user_id", id),
                rowMapper
        );
    }

    public User save(User item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update("insert into users(user_name, isInside, room_id)" +
                            " values ( :user_name, :isInside, :room_id) ",
                    new MapSqlParameterSource(Map.of(
                            "user_name", item.getName(),
                            "isInside", item.isInside(),
                            "room_id", item.getRoom().getId()
                    )),
                    keyHolder
            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }
        template.update("update users set user_id = :user_id, user_name = :user_name, isInside = :isInside, room_id = :room_id" +
                        " where user_id = :user_id",
                Map.of(
                        "user_id", item.getId(),
                        "user_name", item.getName(),
                        "isInside", item.isInside(),
                        "room_id", item.getRoom().getId()
                )
        );
        return getById(item.getId());
    }


}
