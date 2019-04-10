package nc.test.service.impl;

import nc.test.dao.impl.OrdersDaoImpl;
import nc.test.model.Orders;
import nc.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersDaoImpl orderDao;

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
            orderDao.updateOrders(orders);
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
