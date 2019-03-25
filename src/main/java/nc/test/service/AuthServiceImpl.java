package nc.test.service;

import nc.test.controller.AuthController;
import nc.test.dao.interfaces.UserDao;
import nc.test.security.SecurityCheck;
import nc.test.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//todo maxim
//убрать лишний импорт
//ctrl+alt+l - автоформатирование.. пользуйся..
//все интерфейсы как nc.test.service.interfaces.AuthService перенести в папку nc.test.service
//здесь nc.test.service создать папку impl.. и классы имплементации туда.. =)
//в блоках if case всегда используй {}
//избегай много точек в одной строке

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
