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

    private static final String SQL_INSERT =
            "insert into :table (username, first_name, last_name, phone_number) " +
                    "values (:username, :first_name ,:last_name,:phone_number)";

    private static final String SQL_UPDATE =
            "update :table set first_name = :first_name," +
                    "last_name = :last_name," +
                    "phone_number = :phone_number " +
                    "where username = :username";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<MutantOperCust> getAllOperators() {
        return jdbcTemplate.query(SELECT_ALL, new OperCustMapper());
    }

    @Override
    public void insert(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = operatorParams(mutantOperCust);
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void update(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = operatorParams(mutantOperCust);
        jdbcTemplate.update(SQL_UPDATE, params);
    }


    private MapSqlParameterSource operatorParams(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", mutantOperCust.getUsername());
        params.addValue("first_name", mutantOperCust.getFirstName());
        params.addValue("last_name", mutantOperCust.getLastName());
        params.addValue("phone_number", mutantOperCust.getPhone());
        params.addValue("table", mutantOperCust.getTable());
        return params;
    }
}
