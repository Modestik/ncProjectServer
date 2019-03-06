package nc.test.service;

import nc.test.model.Users;

public interface UserService {
    Users getUserByLogin(String username);

    void create(Users user);

    void update(Users user);

    void deleteUserByLogin(String username);
}
