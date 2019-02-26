package nc.test.service;

import nc.test.model.Order;

public interface OrderService {

    Order getOrder(int orderId);

    void createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(int id);
}
