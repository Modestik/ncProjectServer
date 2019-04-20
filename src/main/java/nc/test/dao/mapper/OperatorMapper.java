package nc.test.dao.mapper;

import nc.test.model.Operator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OperatorMapper implements RowMapper<Operator> {
    public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {
        Operator operator = new Operator();
        operator.setUsername(rs.getString("username"));
        operator.setFirstName(rs.getString("first_name"));
        operator.setLastName(rs.getString("last_name"));
        operator.setPhone(rs.getString("phone_number"));
        return operator;
    }
}
