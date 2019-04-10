package nc.test.service;

import nc.test.model.Sessions;

public interface SessionService {

    void createSession(String id, String username);

    void updateSession(String id);

    void deleteSession(String id);

    Sessions getSession(String id);
}
