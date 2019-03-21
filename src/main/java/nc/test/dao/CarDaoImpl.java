package nc.test.dao;

import nc.test.dao.interfaces.CarDao;
import nc.test.dao.mapper.CarMapper;
import nc.test.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private final String SELECT_FREE_CARS =
            "select  c.number,\n" +
                    "c.model,\n" +
                    "c.color\n" +
                    "from cars c\n" +
                    "  left join drivers d on\n" +
                    "    c.number = d.car_number\n" +
                    "where d.username isnull ;";

    private final String SQL_INSERT =
            "insert into cars (number, model, color) values (:number, :model ,:color)";

    private final String SQL_COUNT =
            "select count(number) from cars where number = :number limit 1";

    private final String SELECT_ALL =
                    "select c.number,\n" +
                    "       c.model,\n" +
                    "       c.color,\n" +
                    "       d.username as driver\n" +
                    "from cars c\n" +
                    "       left join drivers d on\n" +
                    "  c.number = d.car_number;";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Car> getFreeCars() {
        return jdbcTemplate.query(SELECT_FREE_CARS, new CarMapper());
    }

    @Override
    public List<Car> getAllCars() {
        return jdbcTemplate.query(SELECT_ALL, new CarMapper());
    }

    @Override
    public void insert(Car car) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("number", car.getNumber());
        params.addValue("model", car.getModel());
        params.addValue("color", car.getColor());
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public boolean carIsEmpty(String number) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("number", number);
        String count = jdbcTemplate.queryForObject(SQL_COUNT, params, String.class);
        if (count.equals("0")) {
            return true;
        } else {
            return false;
        }
    }
}
