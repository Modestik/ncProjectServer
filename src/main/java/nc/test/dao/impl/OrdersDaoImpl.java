package nc.test.dao.impl;

import nc.test.dao.OrdersDao;
import nc.test.dao.mapper.OrdersMapper;
import nc.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    private static final String SELECT_ALL = "SELECT * from ORDERS";

    private static final String SQL_GET_BY_ID =
            "select * from orders where id_order = :id_order";

    private static final String SELECT_BY_CUST = "SELECT * from ORDERS WHERE customer = :customer";

    private static final String SQL_UPDATE =
            "update orders set point_from = :point_from, " +
                    "point_to = :point_to," +
                    "cost = :cost," +
                    "description = :description," +
                    "start_time = :start_time," +
                    "end_time = :end_time," +
                    "status = :status," +
                    "driver = :driver," +
                    "customer = :customer " +
                    "where id_order = :id_order";


    private static final String SQL_CREATE =
            "insert into orders (customer, point_from, point_to, cost, start_time, end_time, status, driver, description)" +
                    "values(:customer,:point_from,:point_to,:cost,:start_time,:end_time,:status,:driver,:description)";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public List<Orders> selectAllOrders() {
        return jdbcTemplate.query(SELECT_ALL, new OrdersMapper());

    }

    @Override
    public void updateOrder(Orders orders) {
        MapSqlParameterSource params = getParams(orders);
        jdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void createOrders(Orders orders) {
        MapSqlParameterSource params = getParams(orders);
        jdbcTemplate.update(SQL_CREATE, params);
    }

    @Override
    public List<Orders> selectOrdersByCustomer(String custname) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customer", custname);
        return jdbcTemplate.query(SELECT_BY_CUST, params, new OrdersMapper());

    }

    @Override
    public Optional<Orders> getOrderById(int orderId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_order", orderId);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BY_ID,
                            params,
                            ordersMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private MapSqlParameterSource getParams(Orders order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_order", order.getIdOrder());
        params.addValue("point_from", order.getPointFrom());
        params.addValue("point_to", order.getPointTo());
        params.addValue("cost", order.getCost());
        params.addValue("description", order.getDescription());
        params.addValue("start_time", order.getStartTime());
        params.addValue("end_time", order.getEndTime());
        params.addValue("status", order.getStatus());
        params.addValue("driver", order.getDriver());
        params.addValue("customer", order.getCustomer());
        return params;
    }

}
