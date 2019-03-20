package nc.test.service.interfaces;

import nc.test.model.MutantOperCust;
import nc.test.model.Users;
import org.apache.tomcat.jni.User;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    Users getUserByLogin(String username);

    HttpStatus createUsers(Users users);

    boolean updateUsers(Users[] jsonStr);

    boolean deleteUserByLogin(String username);

    List<Users> getAllEmployees();


}
