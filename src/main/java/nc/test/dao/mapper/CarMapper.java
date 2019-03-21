package nc.test.dao.mapper;

import nc.test.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setNumber(rs.getString("number"));
        car.setModel(rs.getString("model"));
        car.setColor(rs.getString("color"));
        car.setDriver(rs.getString("driver"));
        return car;
    }

}
