package nc.test.service;

import nc.test.dao.DriverDao;
import nc.test.dao.UserDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Driver;
import nc.test.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private DriverDao driverDao;

    @Override
    public Users getUserByLogin(String username) {
        return userDao.getUserByLogin(username).orElseThrow(() -> new NotFoundException(username));
    }

    @Override
    public void create(Users user) {
        userDao.insert(user);
    }

    @Override
    public void createDriver(Driver driver) {
        driverDao.insert(driver);
    }

    @Override
    public void update(Users user) {
        userDao.update(user);
    }

    @Override
    public void deleteUserByLogin(String username) {
        userDao.deleteUserByLogin(username);
    }

    @Override
    public boolean loginIsEmpty (String username) {
        return userDao.loginIsEmpty(username);
    }
}
