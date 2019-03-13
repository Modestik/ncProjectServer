package nc.test.dao;

import nc.test.dao.interfaces.OperCustDao;
import nc.test.dao.mapper.OperCustMapper;
import nc.test.model.MutantOperCust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperCustDaoImpl implements OperCustDao {


    private final String SELECT_ALL =
            "select * from operators";

    private final String SQL_DELETE =
            "delete from operators where username = :username";



    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<MutantOperCust> getAllOperators() {
        return jdbcTemplate.query(SELECT_ALL, new OperCustMapper());
    }

    @Override
    public void insert(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = operatorParams(mutantOperCust);
        String url = "insert into " + mutantOperCust.getTable() +
                " (username, first_name, last_name, phone_number) " +
                "values (:username, :first_name ,:last_name,:phone_number)";
        jdbcTemplate.update(url, params);
    }

    @Override
    public void update(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = operatorParams(mutantOperCust);
        String url = "update " + mutantOperCust.getTable() +
                " set first_name = :first_name," +
                "last_name = :last_name," +
                "phone_number = :phone_number " +
                "where username = :username";
        jdbcTemplate.update(url, params);
    }


    private MapSqlParameterSource operatorParams(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", mutantOperCust.getUsername());
        params.addValue("first_name", mutantOperCust.getFirstName());
        params.addValue("last_name", mutantOperCust.getLastName());
        params.addValue("phone_number", mutantOperCust.getPhone());
        return params;
    }
    @Override
    public void deleteUserByLogin(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        jdbcTemplate.update(SQL_DELETE, params);
    }
}
