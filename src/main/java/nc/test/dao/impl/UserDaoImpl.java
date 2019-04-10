package nc.test.dao.impl;

import nc.test.dao.UserDao;
import nc.test.dao.mapper.UserMapper;
import nc.test.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String SQL_GET_BY_USERNAME =
            "select * from users where username = :username";

    private static final String SQL_INSERT =
            "insert into users (username, password, role) values (:username, :password ,:role)";

    private static final String SQL_UPDATE =
            "update users set password = :password where username = :username";

    private static final String SQL_DELETE =
            "delete from users where username = :username";

    private static final String SQL_COUNT =
            "select count(username) from users where username = :username limit 1";


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /*
     * Получение user по логину(username)
     */
    @Override
    public Optional<Users> getUserByLogin(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BY_USERNAME,
                            params,
                            userMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(Users user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", new BCryptPasswordEncoder().encode(user.getPassword()));
        params.addValue("role", user.getRole());
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void update(Users user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", new BCryptPasswordEncoder().encode(user.getPassword()));
        params.addValue("role", user.getRole());
        jdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void deleteUserByLogin(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        jdbcTemplate.update(SQL_DELETE, params);
    }

    /**
     * Проверяет если такой логин в системе
     * @param username
     * @return true, если нет
     */
    @Override
    public boolean loginIsEmpty(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        String count = jdbcTemplate.queryForObject(SQL_COUNT, params, String.class);
        if (count.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

}
