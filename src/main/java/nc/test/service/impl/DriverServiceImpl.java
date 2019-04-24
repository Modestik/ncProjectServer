package nc.test.service.impl;

import nc.test.dao.DriverDao;
import nc.test.model.Driver;
import nc.test.model.Orders;
import nc.test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Driver getUserByLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Driver driver = driverDao.getDriver(username);
        return driver;
    }

    @Override
    public HttpStatus updateUser(Driver driver) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            driver.setUsername(username);
            Driver driverInDB = getUserByLogin();
            driver.setCarNumber(driverInDB.getCarNumber());
            driverDao.update(driver);
            return HttpStatus.ACCEPTED;
        } catch (Exception ex) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public List<Orders> getOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Orders> list = driverDao.getOrders(username);
        return list;
    }
}
