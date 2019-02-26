package nc.test.dao;

import nc.test.model.Order;

import java.util.Optional;

public interface OrderDao {

    Optional<Order> getById(int id);
    void insert(Order order);
    void update(Order order);
    void deleteById(int id);
}
