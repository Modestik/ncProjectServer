package nc.test.dao;

import nc.test.model.Users;

import java.util.Optional;

public interface UserDao {
    Optional<Users> getUserByLogin(String username);
}
