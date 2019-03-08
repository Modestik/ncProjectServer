package nc.test.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.io.JsonEOFException;
import nc.test.exception.NotFoundException;
import nc.test.model.Driver;
import nc.test.model.Users;
import nc.test.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
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


    /**
     * Добавление сотрудника
     *
     * @param jsonStr
     * @param response
     * @throws IOException
     * @throws JSONException
     */
    @PostMapping("/employee")
    public void createEmployee(@Valid @RequestBody String jsonStr, HttpServletResponse response) throws IOException, JSONException {
        //почти ооп
        try {
            //Spring в @RequestBody не воспринимает JSONObject (получает пустое значение) поэтому конвектор через String
            JSONObject json = new JSONObject(jsonStr);
            //Если пользователь уже есть в системе, выход
            if (userService.loginIsEmpty(json.get("username").toString())) {
                Users user = new Users();
                user.setUsername(json.get("username").toString());
                user.setPassword(json.get("password").toString());
                user.setRole(json.get("role").toString());
                userService.create(user);

                if (user.getRole().equals("DRIVER")) {
                    Driver driver = new Driver();
                    driver.setUsername(user.getUsername());
                    driver.setFirstName(json.get("firstName").toString());
                    driver.setLastName(json.get("lastName").toString());
                    driver.setPhone(json.get("phone").toString());
                    userService.createDriver(driver);
                }
                response.setStatus(HttpServletResponse.SC_CREATED); //201
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE); //406
                response.getWriter().println("Name already taken");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
