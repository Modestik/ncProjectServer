package nc.test.controller;

import nc.test.model.Orders;
import nc.test.model.Users;
import nc.test.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping
    public List<Orders> getOrders(HttpServletResponse response) throws IOException {

        List<Orders> ob=orderService.selectAllOrders();
        return ob;
    }
    @PostMapping
    public void updateOrders(@Valid @RequestBody Orders orders, HttpServletResponse response) throws IOException {
        try {
            orderService.updatwOrders(orders);
            response.setStatus(HttpServletResponse.SC_OK); //200
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE); //406
        }
    }
}
