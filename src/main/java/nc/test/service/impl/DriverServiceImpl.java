package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.dao.DriverDao;
import nc.test.model.Driver;
import nc.test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverDao driverDao;

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> list = driverDao.getAllDrivers();
        log.info(new StringBuilder()
                .append("Получена список водителей из ")
                .append(list.size())
                .append(" человек ")
        );
        return list;
    }

    @Override
    public Driver getUserByLogin(String username) {
        return driverDao.getDriver(username);
    }
}
