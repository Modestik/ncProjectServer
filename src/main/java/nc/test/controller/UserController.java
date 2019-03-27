package nc.test.controller;

import nc.test.model.Users;
import nc.test.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Контроллер для добавления сотрудника
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody Users users) {
        return ResponseEntity.status(userService.createUsers(users)).build();
    }

    /**
     * Контроллер для update
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Users[] users) {
        return userService.updateUsers(users) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Контроллер возвращающий всех сотрудников системы
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Users> getAllEmployees() {
        List<Users> list = userService.getAllEmployees();
        return list;
        //return new ResponseEntity(responce, responce == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody String username) {
        return userService.deleteUserByLogin(username) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
