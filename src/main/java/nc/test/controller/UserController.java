package nc.test.controller;

import nc.test.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

/*
// пока не нужно
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @RequestBody Users user) {
        userService.update(user);
    }

    @DeleteMapping()
    public void deleteProfile(@Valid @RequestBody Users user) {
        userService.deleteUserByLogin(user.getUsername());
    }*/


    /**
     * Контроллер для добавления сотрудника
     *
     * @param jsonStr
     */
    @PostMapping()
    public ResponseEntity createUser(@Valid @RequestBody String jsonStr) {
        return ResponseEntity.status(userService.createUsers(jsonStr)).build();
    }

    /**
     * Контроллер для update
     *
     * @param jsonStr
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody String jsonStr) {
        return userService.updateUsers(jsonStr) ?
                ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Контроллер возвращающий всех сотрудников системы
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<String> getAllEmployees() {
        String responce = userService.getAllEmployees();
        return new ResponseEntity(responce, responce == "" ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

}
