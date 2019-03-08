package nc.test.dao.mapper;

import nc.test.model.Driver;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DriverMapper implements RowMapper<Driver> {
    public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
        Driver driver = new Driver();
        driver.setUsername(rs.getString("username"));
        driver.setFirstName(rs.getString("first_name"));
        driver.setLastName(rs.getString("last_name"));
        driver.setPhone(rs.getString("phone_number"));
        driver.setCarNumber(rs.getString("car_number"));
        driver.setRealPoint(rs.getString("driver_real_point"));
        return driver;
    }
}

