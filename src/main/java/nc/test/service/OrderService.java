package nc.test.service;

import nc.test.model.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> selectAllOrders();
}
