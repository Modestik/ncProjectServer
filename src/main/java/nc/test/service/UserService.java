package nc.test.service;

import nc.test.model.Driver;
import nc.test.model.Users;

public interface UserService {

    Users getUserByLogin(String username);

    void create(Users user);
    void createDriver(Driver driver);
    void updateDriver(Driver driver);

    void update(Users user);

    void deleteUserByLogin(String username);

    boolean loginIsEmpty(String username);
}
