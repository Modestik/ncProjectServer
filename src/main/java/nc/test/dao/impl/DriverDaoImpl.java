package nc.test.dao.impl;

import nc.test.dao.DriverDao;
import nc.test.dao.mapper.CustomerMapper;
import nc.test.dao.mapper.DriverMapper;
import nc.test.dao.mapper.OrdersMapper;
import nc.test.model.Driver;
import nc.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDaoImpl implements DriverDao {

    private final String SELECT_ALL = "select * from drivers";

    private static final String SQL_INSERT =
            "insert into drivers (username, first_name, last_name, phone_number) " +
                    "values (:username, :first_name , :last_name,:phone_number)";

    private static final String SQL_UPDATE =
            "update drivers set first_name = :first_name," +
                    "last_name = :last_name," +
                    "phone_number = :phone_number, " +
                    "car_number = :car_number " +
                    "where username = :username";

    private static final String SQL_DELETE =
            "delete from drivers where username = :username";

    private final String SELECT_BY_DRIVER = "SELECT * from drivers WHERE  username = :username";

    private final String SQL_MY_ORDERS = "select * from orders where driver = :driver";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> getAllDrivers() {
        return jdbcTemplate.query(SELECT_ALL, new DriverMapper());
    }

    @Override
    public void insert(Driver driver) {
        MapSqlParameterSource params = driverParams(driver);
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void update(Driver driver) {
        MapSqlParameterSource params = driverParams(driver);
        params.addValue("car_number", driver.getCarNumber());
        jdbcTemplate.update(SQL_UPDATE, params);
    }

    private MapSqlParameterSource driverParams(Driver driver) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", driver.getUsername());
        params.addValue("first_name", driver.getFirstName());
        params.addValue("last_name", driver.getLastName());
        params.addValue("phone_number", driver.getPhone());
        return params;
    }

    @Override
    public void deleteUserByLogin(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        jdbcTemplate.update(SQL_DELETE, params);
    }

    @Override
    public Driver getDriver(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", name);
        return jdbcTemplate.query(SELECT_BY_DRIVER, params, new DriverMapper()).get(0);
    }

    @Override
    public List<Orders> getOrders(String driver) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("driver", driver);
        return jdbcTemplate.query(SQL_MY_ORDERS, params, new OrdersMapper());
    }
}
