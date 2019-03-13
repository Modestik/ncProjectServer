package nc.test.service.interfaces;

import nc.test.model.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> selectAllOrders();
    List<Orders> selectOrdersByCustomer(String custname);
    void updatwOrders(Orders orders);
}
