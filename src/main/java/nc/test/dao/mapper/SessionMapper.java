package nc.test.dao.mapper;

import nc.test.model.Sessions;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class SessionMapper implements RowMapper<Sessions> {

    @Override
    public Sessions mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sessions sessions = new Sessions();
        sessions.setId(rs.getString("id"));
        sessions.setUsername(rs.getString("username"));
        sessions.setTimeOfBegin(rs.getObject("time_of_begin", LocalDateTime.class));
        sessions.setTimeRecentActivity(rs.getObject("time_recent_activity", LocalDateTime.class));
        return sessions;
    }
}
