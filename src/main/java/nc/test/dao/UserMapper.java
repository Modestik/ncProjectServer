package nc.test.dao;

import nc.test.model.Users;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users();
        users.setId(rs.getInt("id_user"));
        users.setFirstName(rs.getString("first_name"));
        users.setLastName(rs.getString("last_name"));
        users.setUsername(rs.getString("username"));
        users.setPassword(rs.getString("password"));
        users.setRole(rs.getString("role"));
        return users;
    }
}
