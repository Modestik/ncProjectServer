package nc.test.dao;

import nc.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrdersDao {


    final String SELECT_ALL = "SELECT * from ORDERS";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Orders> selectAllOrders() {
        return jdbcTemplate.query(SELECT_ALL, new OrdersMapper());
    }
    private static final class OrdersMapper implements RowMapper<Orders> {
        public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
            Orders o = new Orders();
            o.setCustomer(rs.getString("customer"));
            o.setStatus(rs.getString("status"));
            o.setDriver(rs.getString("driver"));
            o.setDescription(rs.getString("description"));
            o.setPoint_from(rs.getString("point_from"));
            o.setPoint_to(rs.getString("point_to"));
            o.setId_order(rs.getInt("id_order"));
            o.setCost(rs.getDouble("cost"));
            o.setStart_time(rs.getDate("start_time"));
            o.setEnd_time(rs.getDate("end_time"));
            return o;
        }
    }

}
