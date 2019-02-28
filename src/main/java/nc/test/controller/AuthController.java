package nc.test.controller;

import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

  /*  test
    @GetMapping
    public String sayHi() {
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
       // System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        // Users user = userService.getUserByLogin( )
        return "hi";
    }*/

    /*
     * Метод возвращающий роль
     */
    @GetMapping("/role")
    public String getRole() {
        //return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        return userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRole();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody Users request) {
        userService.create(request);
    }


    @GetMapping(value = "/{orderId:\\d+}")
    public Users getUser(@PathVariable int orderId) {
        return userService.getUserByLogin("admin");
    }

}
