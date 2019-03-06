package nc.test.controller;

import nc.test.exception.NotFoundException;
import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Контроллер создающий пользователя
     *
     * @param newUser пользователь которого надо создать
     */
    @PostMapping
    public void createUser(@Valid @RequestBody Users newUser, HttpServletResponse response) throws IOException {
        //почти ооп
        try {
            userService.getUserByLogin(newUser.getUsername());
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE); //406
            response.getWriter().println("Name already taken");
        } catch (NotFoundException e) {
            userService.create(newUser);
            response.setStatus(HttpServletResponse.SC_CREATED); //201
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCar(@Valid @RequestBody Users user) {
        userService.update(user);
    }

    @DeleteMapping()
    public void deleteProfile(@Valid @RequestBody Users user) {
        userService.deleteUserByLogin(user.getUsername());
    }

}
