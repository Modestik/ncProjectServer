package nc.test.dao;

import nc.test.dao.mapper.DriverMapper;
import nc.test.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDaoImpl implements DriverDao {

    final String SELECT_ALL = "select * from drivers";
    private static final String SQL_INSERT =
            "insert into drivers (username, first_name, last_name, phone_number) values (:username, :first_name ,:last_name,:phone_number)";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> getAllDrivers() {
        return jdbcTemplate.query(SELECT_ALL, new DriverMapper());
    }

    @Override
    public void insert(Driver driver) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", driver.getUsername());
        params.addValue("first_name", driver.getFirstName());
        params.addValue("last_name", driver.getLastName());
        params.addValue("phone_number", driver.getPhone());
        jdbcTemplate.update(SQL_INSERT, params);
    }
}
