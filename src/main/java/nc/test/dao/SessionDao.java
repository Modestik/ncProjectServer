package nc.test.dao;

import nc.test.model.Sessions;
import nc.test.model.Users;

import java.util.Optional;

public interface SessionDao {

    Optional<Sessions> getSession(int id, String username);

    int insert(String username);

    void update(int id);

    void deleteSessionById(int id);
}
