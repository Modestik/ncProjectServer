package nc.test.service;

import nc.test.model.Users;

public interface UserService {
    Users getUserByLogin (String username);
}
