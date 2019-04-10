package nc.test.dao;

import nc.test.model.Driver;
import nc.test.model.Users;

import java.util.List;

public interface DriverDao {
    List<Driver> getAllDrivers();
    void insert(Driver driver);
    void update(Driver driver);
    void deleteUserByLogin(String username);
    Driver getDriver(String name);
}
