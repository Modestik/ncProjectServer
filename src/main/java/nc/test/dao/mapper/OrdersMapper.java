package nc.test.dao.mapper;

import nc.test.model.Orders;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersMapper implements RowMapper<Orders> {
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders o = new Orders();
        o.setCustomer(rs.getString("customer"));
        o.setStatus(rs.getString("status"));
        o.setDriver(rs.getString("driver"));
        o.setWeight(rs.getString("weight"));
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