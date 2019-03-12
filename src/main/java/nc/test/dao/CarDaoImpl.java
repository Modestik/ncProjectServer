package nc.test.dao;

import nc.test.dao.interfaces.CarDao;
import nc.test.dao.mapper.CarMapper;
import nc.test.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    final String SELECT_ALL = "select  c.number,\n" +
            "        c.model,\n" +
            "        c.color\n" +
            "from cars c\n" +
            "  left join drivers d on\n" +
            "    c.number = d.car_number\n" +
            "where d.username isnull ;";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Car> getFreeCars() {
        return jdbcTemplate.query(SELECT_ALL, new CarMapper());
    }
}
