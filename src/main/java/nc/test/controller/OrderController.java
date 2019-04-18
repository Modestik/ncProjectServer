package nc.test.controller;

import nc.test.model.Orders;
import nc.test.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/orders/all", method = RequestMethod.GET)
    public List<Orders> getOrders() {
        List<Orders> ordersList = orderService.selectAllOrders();
        return ordersList;
    }

    @RequestMapping(value = "/orders/update", method = RequestMethod.PUT)
    public ResponseEntity updateOrders(@RequestBody Orders orders) {
        HttpStatus status = orderService.updateOrders(orders);
        return ResponseEntity.status(status).build();
    }

    @RequestMapping(value = "/orders/create", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Orders orders) {
        HttpStatus status = orderService.createOrders(orders);
        return ResponseEntity.status(status).build();
    }

    @RequestMapping(value = "/orders/customer", method = RequestMethod.GET)
    public List<Orders> getOrdersByCustomer() {
        List<Orders> ordersList = orderService.selectOrdersByCustomer();
        return ordersList;
    }
}
