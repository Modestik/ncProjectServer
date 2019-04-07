package nc.test.service;

import nc.test.model.Sessions;

public interface SessionService {

    void createSession(String username);

    void updateSession(int id);

    Sessions getSession(String username);
}
