package nc.test.controller;

import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Контроллер для добавления сотрудника
     */
    @RequestMapping(value = "/user/customers", method = RequestMethod.POST)
    public ResponseEntity createCustomers(@RequestBody Users users) {
        HttpStatus status = userService.createCustomers(users);
        return ResponseEntity.status(status).build();
    }

    /**
     * Контроллер для добавления сотрудника
     */
    @RequestMapping(value = "/user/employees", method = RequestMethod.POST)
    public ResponseEntity createEmployees(@RequestBody Users users) {
        return ResponseEntity.status(userService.createEmployees(users)).build();
    }

    /**
     * Контроллер возвращающий всех сотрудников системы
     */
    @RequestMapping(value = "/user/employees", method = RequestMethod.GET)
    public List<Users> getAllEmployees() {
        List<Users> list = userService.getAllEmployees();
        return list;
    }

    /**
     * Контроллер для update
     */
    @RequestMapping(value = "/user/employees/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Users[] users) {
        HttpStatus status = userService.updateEmployees(users);
        return ResponseEntity.status(status).build();
    }


    /**
     * Контроллер для удаления сотрудников
     */
    @RequestMapping(value = "/user/employees/", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody String username) {
        HttpStatus status = userService.deleteUserByLogin(username);
        return ResponseEntity.status(status).build();
    }
}
