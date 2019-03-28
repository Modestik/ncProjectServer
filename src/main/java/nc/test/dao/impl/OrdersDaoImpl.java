package nc.test.dao.impl;

import nc.test.dao.OrdersDao;
import nc.test.dao.mapper.OrdersMapper;
import nc.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

    private static final String SQL_CREATE =
            "insert into orders (customer, point_from, point_to, cost, weight, start_time, end_time, status, driver, description)"+
                   "values(:customer,:point_from,:point_to,:cost,:weight,:start_time,:end_time,:status,:driver,:description)";
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
        params.addValue("pointFrom",order.getPointFrom());
        params.addValue("pointTo", order.getPointTo());
        params.addValue("cost", order.getCost());
        params.addValue("weight", order.getWeight());
        params.addValue("description", order.getDescription());
        params.addValue("startTime", order.getStartTime());
        params.addValue("endTime", order.getEndTime());
        params.addValue("status", order.getStatus());
        params.addValue("driver", order.getDriver());
        params.addValue("customer", order.getCustomer());
        params.addValue("idOrder", order.getIdOrder());
        return params;
    }
    public void createOrders(Orders order)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pointFrom",order.getPointFrom());
        params.addValue("pointTo", order.getPointTo());
        params.addValue("cost", order.getCost());
        params.addValue("weight", order.getWeight());
        params.addValue("description", order.getDescription());
        params.addValue("startTime", order.getStartTime());
        params.addValue("endTime", order.getEndTime());
        params.addValue("status", order.getStatus());
        params.addValue("driver", order.getDriver());
        params.addValue("customer", order.getCustomer());
        jdbcTemplate.update(SQL_CREATE,params );
    }

    public void updateOrders(Orders orders) {
        jdbcTemplate.update(SQL_UPDATE, getParams(orders));
    }



}
