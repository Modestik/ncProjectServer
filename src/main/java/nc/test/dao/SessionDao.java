package nc.test.dao;

import nc.test.model.Sessions;
import nc.test.model.Users;

import java.util.Optional;

public interface SessionDao {

    Optional<Sessions> getSession(String id);

    void insert(String id, String username);

    void update(String id);

    void deleteSessionById(String id);
}
