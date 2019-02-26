package nc.test.service;

import nc.test.dao.OrderDao;
import nc.test.exception.OrderNotFoundException;
import nc.test.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

   /* @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
*/
    @Override
    public Order getOrder(int orderId) {
        return orderDao.getById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public void createOrder(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void updateOrder(Order order) {
        Order orderUpdate = orderDao.getById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException(order.getId()));

        orderUpdate.setName(order.getName())
                .setStartPointLongitude(order.getStartPointLongitude())
                .setStartPointWidth(order.getStartPointWidth())
                .setEndPointLongitude(order.getEndPointLongitude())
                .setEndPointWidth(order.getEndPointWidth())
                .setStatus(order.getStatus());

        orderDao.update(orderUpdate);
    }

    @Override
    public void deleteOrder(int id) {
        Order order = orderDao.getById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderDao.deleteById(id);
    }
}

