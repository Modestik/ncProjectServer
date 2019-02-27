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
public class UserDaoImpl  implements UserDao{

    private static final String SQL_GET_BY_USERNAME =
            "select * from users where username = :username";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


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


}
