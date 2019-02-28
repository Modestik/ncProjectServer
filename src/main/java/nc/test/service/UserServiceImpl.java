package nc.test.service;

import nc.test.dao.UserDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public Users getUserByLogin(String username) {
        return userDao.getUserByLogin(username).orElseThrow(() -> new NotFoundException(username));
    }

    @Override
    public void create(Users user) {
        userDao.insert(user);
    }
}
