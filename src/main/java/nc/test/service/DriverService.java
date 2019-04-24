package nc.test.service;

import nc.test.model.Driver;
import nc.test.model.Orders;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface DriverService {

    List<Driver> getDrivers();

    Driver getUserByLogin();

    HttpStatus updateUser(Driver driver);

    List<Orders> getOrders();
}
