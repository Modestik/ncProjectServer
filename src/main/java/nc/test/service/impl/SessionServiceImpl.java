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
    public void createSession(String id, String username) {
        sessionDao.insert(id, username);
    }

    @Override
    public void updateSession(String id) {
        sessionDao.update(id);
    }

    @Override
    public void deleteSession(String id) {
        sessionDao.deleteSessionById(id);
    }

    @Override
    public Sessions getSession(String id) {
        return sessionDao.getSession(id).orElseThrow(() -> new NotFoundException(id));
    }
}
