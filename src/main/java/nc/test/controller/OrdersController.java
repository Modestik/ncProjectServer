package nc.test.controller;

import nc.test.model.Orders;
import nc.test.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping
    public List<Orders> getOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Orders> ob=orderService.selectAllOrders();
        return ob;
    }
}
