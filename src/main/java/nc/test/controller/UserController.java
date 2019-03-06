package nc.test.controller;

import nc.test.exception.NotFoundException;
import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Контроллер создающий пользователя
     *
     * @param newUser пользователь которого надо создать
     */
    @PostMapping
    public void createUser(@Valid @RequestBody Users newUser, HttpServletResponse response)  throws IOException {
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

/*    @PutMapping(value = "/{carId:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCar(
            @Valid @RequestBody Users user
            @PathVariable int carId
    ) {
        carService.updateCar(
                request.getName(),
                request.getMarka(),
                request.getYear(),
                carId
        );
    }

    @DeleteMapping(value = "/{carId:\\d+}")
    public void deleteProfile(@PathVariable int carId) {
        userService.delete(carId);
    }*/

}
