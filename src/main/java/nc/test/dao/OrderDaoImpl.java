package nc.test.dao;

import nc.test.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final String SQL_GET_BY_ID =
            "select id, name, start_long, start_width, end_long, end_width, status from orders where id = :id";

    private static final String SQL_INSERT =
            "insert into orders (name, start_long, start_width, end_long, end_width, status) values (:name, :start_long, :start_width, :end_long ,:end_width,:status)";

    private static final String SQL_UPDATE =
            "update orders set name = :name, start_long = :start_long, start_width = :start_width, end_long = :end_long, end_width = :end_width, status =:status where id = :id";

    private static final String SQL_DELETE =
            "delete from orders where id = :id";

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Order> getById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BY_ID,
                            params,
                            orderMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(Order order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", order.getName());
        params.addValue("start_long", order.getStartPointLongitude());
        params.addValue("start_width", order.getStartPointWidth());
        params.addValue("end_long", order.getEndPointLongitude());
        params.addValue("end_width", order.getEndPointWidth());
        params.addValue("status", order.getStatus());
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void update(Order order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", order.getName());
        params.addValue("start_long", order.getStartPointLongitude());
        params.addValue("start_width", order.getStartPointWidth());
        params.addValue("end_long", order.getEndPointLongitude());
        params.addValue("end_width", order.getEndPointWidth());
        params.addValue("status", order.getStatus());
        params.addValue("id", order.getId());
        jdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void deleteById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(SQL_DELETE, params);
    }
}
