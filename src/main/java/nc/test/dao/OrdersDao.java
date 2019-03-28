package nc.test.dao;

import nc.test.model.Orders;

import java.util.List;

public interface OrdersDao {
    List<Orders> selectAllOrders();
    void updateOrders(Orders orders);
    void createOrders(Orders orders);
    List<Orders> selectOrdersByCustomer(String custname);
}
