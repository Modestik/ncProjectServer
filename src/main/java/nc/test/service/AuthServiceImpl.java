package nc.test.service;

import nc.test.controller.AuthController;
import nc.test.dao.interfaces.UserDao;
import nc.test.security.SecurityCheck;
import nc.test.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDao userDao;

    @Override
    public String getRole(String basic ) {
        if (SecurityCheck.checkBasicAuth(basic))
            return userDao.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).get().getRole();
        else {
            return "";
        }
    }
}
