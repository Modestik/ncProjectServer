package nc.test.dao;

import nc.test.dao.interfaces.OperatorDao;
import nc.test.dao.mapper.OperatorMapper;
import nc.test.model.MutantOperCust;
import nc.test.model.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperatorDaoImpl implements OperatorDao {


    private final String SELECT_ALL =
            "select * from operators";

    private final String SQL_DELETE =
            "delete from operators where username = :username";

    private final String SQL_INSERT =
            "insert into operators (username, first_name, last_name, phone_number) " +
                    "values (:username, :first_name ,:last_name,:phone_number)";

    private final String SQL_UPDATE =
            "update operators set first_name = :first_name, " +
                    "last_name = :last_name, " +
                    "phone_number = :phone_number " +
                    "where username = :username";



    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Operator> getAllOperators() {
        return jdbcTemplate.query(SELECT_ALL, new OperatorMapper());
    }

    @Override
    public void insert(Operator operator) {
        MapSqlParameterSource params = operatorParams(operator);
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void update(Operator operator) {
        MapSqlParameterSource params = operatorParams(operator);
        jdbcTemplate.update(SQL_UPDATE, params);
    }

    private MapSqlParameterSource operatorParams(Operator operator) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", operator.getUsername());
        params.addValue("first_name", operator.getFirstName());
        params.addValue("last_name", operator.getLastName());
        params.addValue("phone_number", operator.getPhone());
        return params;
    }

    @Override
    public void deleteUserByLogin(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        jdbcTemplate.update(SQL_DELETE, params);
    }
}
