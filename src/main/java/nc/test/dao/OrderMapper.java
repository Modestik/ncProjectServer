package nc.test.dao;

import nc.test.model.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setName(rs.getString("name"));
        order.setStartPointLongitude(rs.getFloat("start_long"));
        order.setStartPointWidth(rs.getFloat("start_width"));
        order.setEndPointLongitude(rs.getFloat("end_long"));
        order.setEndPointWidth(rs.getFloat("end_width"));
        order.setStatus(rs.getString("status"));
        return order;
    }
}
