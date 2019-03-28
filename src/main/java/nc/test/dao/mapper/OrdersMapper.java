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
        o.setPointFrom(rs.getString("point_from"));
        o.setPointTo(rs.getString("point_to"));
        o.setIdOrder(rs.getInt("id_order"));
        o.setCost(rs.getDouble("cost"));
        o.setStartTime(rs.getDate("start_time"));
        o.setEndTime(rs.getDate("end_time"));
        return o;
    }
}