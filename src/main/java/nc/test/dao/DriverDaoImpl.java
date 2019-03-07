package nc.test.dao;

import nc.test.dao.mapper.DriverMapper;
import nc.test.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDaoImpl implements DriverDao{

    final String SELECT_ALL = "select * from drivers";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> getAllDrivers() {
        return jdbcTemplate.query(SELECT_ALL, new DriverMapper());
    }
}
