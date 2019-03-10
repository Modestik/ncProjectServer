package nc.test.service;

import nc.test.dao.OrdersDao;
import nc.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrdersDao orderDao;
    @Override
    public List<Orders> selectAllOrders() {
        return orderDao.selectAllOrders();
    }
    @Override
    public void updatwOrders(Orders orders) {
        orderDao.updateOrders(orders);
    }
}
