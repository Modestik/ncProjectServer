package nc.test.service.impl;

import nc.test.dao.OrdersDao;
import nc.test.model.OrderStatus;
import nc.test.model.Orders;
import nc.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public List<Orders> selectOrdersByCustomer(String custname) {
        return orderDao.selectOrdersByCustomer(custname);
    }

    @Override
    public HttpStatus updateOrders(Orders orders) {
        try {
            Orders orderInDB = orderDao.getOrderById(orders.getIdOrder()).get();
            if (orders.getStatus().equals(OrderStatus.ASSIGNED)) {
                orderInDB.setDriver(orders.getDriver());
            }
            if (orders.getStatus().equals(OrderStatus.RESOLVED)) {
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
    public HttpStatus createOrders(Orders orders) {
        try {
            orderDao.createOrders(orders);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
