package nc.test.dao.impl;

import nc.test.dao.SessionDao;
import nc.test.dao.mapper.SessionMapper;
import nc.test.model.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class SessionDaoImpl implements SessionDao {

    private static final String SQL_GET_BY_USERNAME =
            "select * from sessions where username = :username";

    private static final String SQL_INSERT =
            "insert into sessions (username, time_of_begin, time_recent_activity) values (:username, :time_of_begin ,:time_recent_activity)";

    private static final String SQL_UPDATE =
            "update sessions set time_recent_activity = :time_recent_activity where id = :id";

    private static final String SQL_DELETE =
            "delete from sessions where id = :id";


    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Sessions> getUserByLogin(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BY_USERNAME,
                            params,
                            sessionMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        params.addValue("time_of_begin", LocalDateTime.now());
        params.addValue("time_recent_activity", LocalDateTime.now());
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void update(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("time_recent_activity", LocalDateTime.now());
        jdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void deleteSessionById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(SQL_DELETE, params);
    }
}
