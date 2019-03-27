package nc.test.controller;

import nc.test.model.Orders;
import nc.test.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Orders> getOrders() {
        List<Orders> ordersList = orderService.selectAllOrders();
        return ordersList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrders(@Valid @RequestBody Orders orders, HttpServletResponse response) {
        try {
            orderService.updateOrders(orders);
            response.setStatus(HttpServletResponse.SC_OK); //200
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE); //406
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createOrder(@Valid @RequestBody Orders orders, HttpServletResponse response) {
        try {
            orderService.createOrders(orders);
            response.setStatus(HttpServletResponse.SC_OK); //200
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE); //406
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Orders> getOrdersByCustomer(@RequestParam("custname") String custname) {
        List<Orders> ob = orderService.selectOrdersByCustomer(custname);
        return ob;
    }
}
