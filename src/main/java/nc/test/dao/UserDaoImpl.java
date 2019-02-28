package nc.test.dao;

import nc.test.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String SQL_GET_BY_USERNAME =
            "select * from users where username = :username";

    private static final String SQL_INSERT =
            "insert into users (first_name, last_name, username, password, role) values (:first_name, :last_name, :username, :password ,:role)";


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

    /*
     * Добавление пользователя в БД
     */
    @Override
    public void insert(Users user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", user.getFirstName());
        params.addValue("last_name", user.getLastName());
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());
        params.addValue("role", user.getRole());
        jdbcTemplate.update(SQL_INSERT, params);
    }


}
