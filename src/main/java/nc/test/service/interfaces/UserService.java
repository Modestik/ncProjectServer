package nc.test.service.interfaces;

import nc.test.model.MutantOperCust;
import nc.test.model.Users;
import org.springframework.http.HttpStatus;

public interface UserService {

    Users getUserByLogin(String username);

    HttpStatus createUsers(String jsonStr);

    boolean updateUsers(String jsonStr);

    boolean deleteUserByLogin(String username);

    String getAllEmployees();


}