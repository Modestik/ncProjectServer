package nc.test.controller;

import nc.test.model.Customer;
import nc.test.model.Driver;
import nc.test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/driver/name", method = RequestMethod.GET)
    public Driver getUser(@RequestParam("name") String name) {
        Driver driver = driverService.getUserByLogin(name);
        return driver;
    }
}
