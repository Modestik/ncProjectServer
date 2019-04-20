package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.config.parent.SecurityConfig;
import nc.test.dao.DriverDao;
import nc.test.model.Car;
import nc.test.model.Driver;
import nc.test.model.Orders;
import nc.test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Driver> getDrivers() {
        List<Driver> list = driverDao.getDrivers();
        return list;
    }

    @Override
    public Driver getUserByLogin(String username) {
        return driverDao.getDriver(username);
    }

    @Override
    public List<Orders> getOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Orders> list = driverDao.getOrders(username);
        return list;
    }
}
