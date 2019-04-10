package nc.test.service;

import nc.test.model.Sessions;

public interface SessionService {

    int createSession(String username);

    void updateSession(int id);
    void deleteSession(int id);
    Sessions getSession(int id, String username);
}
