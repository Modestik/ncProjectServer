package nc.test.service;

import nc.test.model.Users;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    Users getUserByLogin(String username);

    HttpStatus createEmployees(Users users);

    HttpStatus createCustomers(Users users);

    HttpStatus updateEmployees(Users[] jsonStr);

    HttpStatus deleteUserByLogin(String username);

    List<Users> getAllEmployees();
}
