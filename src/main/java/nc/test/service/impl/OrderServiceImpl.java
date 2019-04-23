package nc.test.service.impl;

import nc.test.dao.OrdersDao;
import nc.test.model.OrderStatus;
import nc.test.model.Orders;
import nc.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersDao orderDao;

    @Override
    public List<Orders> selectAllOrders() {
        return orderDao.selectAllOrders();
    }

    @Override
    public List<Orders> selectOrdersByCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String customer = auth.getName();
        List<Orders> list = orderDao.selectOrdersByCustomer(customer);
        return list;
    }

    @Override
    public HttpStatus updateOrders(Orders orders) {
        try {
            Orders orderInDB = orderDao.getOrderById(orders.getIdOrder()).get();
            OrderStatus status = OrderStatus.valueOf(orders.getStatus());
            if (status == OrderStatus.ASSIGNED) {
                orderInDB.setDriver(orders.getDriver());
            }

            if (status == OrderStatus.CLOSED) {
                orderInDB.setEndTime(LocalDateTime.now());
            }
            orderInDB.setStatus(orders.getStatus());
            orderDao.updateOrder(orderInDB);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public HttpStatus createOrders(Orders order) {
        try {
            //name customer
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            order.setCustomer(username);

            order.setStatus(OrderStatus.OPEN.toString());
            //date
            LocalDateTime date = LocalDateTime.now();
            order.setStartTime(date);
            order.setEndTime(date.plusDays(1));

            orderDao.createOrders(order);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
