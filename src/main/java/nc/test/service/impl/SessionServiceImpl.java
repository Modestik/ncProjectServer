package nc.test.service.impl;

import nc.test.dao.SessionDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Sessions;
import nc.test.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionDao sessionDao;

    @Override
    public void createSession(String username) {
        sessionDao.insert(username);
    }

    @Override
    public void updateSession(int id) {
        sessionDao.update(id);
    }

    @Override
    public Sessions getSession(String username) {
        return sessionDao.getUserByLogin(username).orElseThrow(() -> new NotFoundException(username));
    }
}
