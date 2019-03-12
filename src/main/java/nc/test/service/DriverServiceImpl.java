package nc.test.service;

import nc.test.dao.interfaces.DriverDao;
import nc.test.model.Driver;
import nc.test.service.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.getAllDrivers();
    }
}
