package nc.test.service;

import nc.test.model.Orders;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface OrderService {

    List<Orders> selectAllOrders();

    List<Orders> selectOrdersByCustomer();

    HttpStatus updateOrders(Orders orders);

    HttpStatus createOrders(Orders orders);
}
