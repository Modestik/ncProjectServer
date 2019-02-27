package nc.test.service;

import nc.test.dao.OrderDao;
import nc.test.dao.UserDao;
import nc.test.exception.OrderNotFoundException;
import nc.test.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public Users getUserByLogin(String username) {
        return userDao.getUserByLogin(username).orElseThrow(() -> new OrderNotFoundException(1));
    }
}
