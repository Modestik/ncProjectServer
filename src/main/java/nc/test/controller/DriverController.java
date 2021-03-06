package nc.test.controller;


import nc.test.model.Driver;
import nc.test.model.Operator;
import nc.test.model.Orders;
import nc.test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/driver/myorders", method = RequestMethod.GET)
    public List<Orders> getOrders() {
        List<Orders> ordersList = driverService.getOrders();
        return ordersList;
    }

    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public List<Driver> getDrivers() {
        List<Driver> driverList = driverService.getDrivers();
        return driverList;
    }

    @RequestMapping(value = "/driver/aboutme", method = RequestMethod.GET)
    public Driver getDriver() {
        Driver driver = driverService.getUserByLogin();
        return driver;
    }

    @RequestMapping(value = "/driver/aboutme", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody Driver driver) {
        HttpStatus status = driverService.updateUser(driver);
        return ResponseEntity.status(status).build();
    }
}
