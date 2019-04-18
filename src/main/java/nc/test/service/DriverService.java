package nc.test.service;

import nc.test.model.Driver;
import nc.test.model.Orders;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();

    Driver getUserByLogin(String username);

    List<Orders> getOrders();
}
