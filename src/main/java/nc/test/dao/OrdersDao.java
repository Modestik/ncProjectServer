package nc.test.dao;

import nc.test.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersDao {

    List<Orders> selectAllOrders();

    void updateOrder(Orders orders);

    void createOrders(Orders orders);

    List<Orders> selectOrdersByCustomer(String custname);

    Optional<Orders> getOrderById(int orderId);
}
