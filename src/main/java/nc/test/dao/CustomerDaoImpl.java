package nc.test.dao;

import nc.test.dao.interfaces.CustomerDao;
import nc.test.dao.mapper.CustomerMapper;
import nc.test.dao.mapper.OperatorMapper;
import nc.test.model.MutantOperCust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String SQL_UPDATE =
            "update customers set first_name = :first_name," +
                    "last_name = :last_name," +
                    "phone_number = :phone_number " +
                    "where username = :username";

    private final String SELECT_BY_CUST = "SELECT * from customers WHERE  username = :username";
    public MutantOperCust getCustomer(String name)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username",name);
        return jdbcTemplate.query(SELECT_BY_CUST,params, new CustomerMapper()).get(0);
    }
    private MapSqlParameterSource operatorParams(MutantOperCust mutantOperCust) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", mutantOperCust.getUsername());
        params.addValue("first_name", mutantOperCust.getFirstName());
        params.addValue("last_name", mutantOperCust.getLastName());
        params.addValue("phone_number", mutantOperCust.getPhone());
        return params;
    }
    public void update(MutantOperCust mutantOperCust)
    {
        MapSqlParameterSource params = operatorParams(mutantOperCust);
        jdbcTemplate.update(SQL_UPDATE, params);
    }
}
