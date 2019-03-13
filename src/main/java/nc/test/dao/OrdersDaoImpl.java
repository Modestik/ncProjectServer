package nc.test.dao;

import nc.test.dao.interfaces.OrdersDao;
import nc.test.dao.mapper.OrdersMapper;
import nc.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {


    private final String SELECT_ALL = "SELECT * from ORDERS";
    private final String SELECT_BY_CUST = "SELECT * from ORDERS WHERE customer = :customer";
    private static final String SQL_UPDATE =
            "update orders set point_from = :point_from, " +
                    "point_to = :point_to," +
                    "cost = :cost," +
                    "description = :description," +
                    "weight = :weight," +
                    "start_time = :start_time," +
                    "end_time = :end_time," +
                    "status = :status," +
                    "driver = :driver," +
                    "customer = :customer " +
                    "where id_order = :id_order";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public List<Orders> selectOrdersByCustomer(String custname)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customer",custname);
        return jdbcTemplate.query(SELECT_BY_CUST,params, new OrdersMapper());
    }

    public List<Orders> selectAllOrders() {
        return jdbcTemplate.query(SELECT_ALL, new OrdersMapper());
    }

    private MapSqlParameterSource getParams(Orders order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("point_from",order.getPoint_from());
        params.addValue("point_to", order.getPoint_to());
        params.addValue("cost", order.getCost());
        params.addValue("weight", order.getWeight());
        params.addValue("description", order.getDescription());
        params.addValue("start_time", order.getStart_time());
        params.addValue("end_time", order.getEnd_time());
        params.addValue("status", order.getStatus());
        params.addValue("driver", order.getDriver());
        params.addValue("customer", order.getCustomer());
        params.addValue("id_order", order.getId_order());
        return params;
    }
    public void updateOrders(Orders orders) {
        jdbcTemplate.update(SQL_UPDATE, getParams(orders));
    }



}
