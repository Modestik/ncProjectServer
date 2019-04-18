package nc.test.dao.mapper;

import nc.test.model.Orders;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class OrdersMapper implements RowMapper<Orders> {
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders order = new Orders();
        order.setIdOrder(rs.getInt("id_order"));
        order.setPointFrom(rs.getString("point_from"));
        order.setPointTo(rs.getString("point_to"));
        order.setCost(rs.getDouble("cost"));
        order.setDescription(rs.getString("description"));
        order.setStartTime(rs.getObject("start_time", LocalDateTime.class));
        order.setEndTime(rs.getObject("end_time", LocalDateTime.class));
        order.setStatus(rs.getString("status"));
        order.setDriver(rs.getString("driver"));
        order.setCustomer(rs.getString("customer"));
        return order;
    }
}