package nc.test.dao.mapper;

import nc.test.model.MutantOperCust;
import nc.test.model.Operator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<MutantOperCust> {
    public MutantOperCust mapRow(ResultSet rs, int rowNum) throws SQLException {
        MutantOperCust mutantOperCust = new MutantOperCust();
        mutantOperCust.setUsername(rs.getString("username"));
        mutantOperCust.setFirstName(rs.getString("first_name"));
        mutantOperCust.setLastName(rs.getString("last_name"));
        mutantOperCust.setPhone(rs.getString("phone_number"));
        return mutantOperCust;
    }
}
