package nc.test.dao.interfaces;

import nc.test.model.Orders;

import java.util.List;

public interface OrdersDao {
    List<Orders> selectAllOrders();
    void updateOrders(Orders orders);
}
