package nc.test.controller;

import lombok.var;
import nc.test.model.Users;
import nc.test.security.SecurityCheck;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

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
    public String getRole(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String basic = request.getHeader("Authorization");
        String password = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getPassword();

        if (SecurityCheck.checkBasicAuth(basic, password))
            return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        else
        {
            SecurityContextHolder.clearContext();
            response.sendError(401);
            return null;
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        }
        //String role = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRole();
        //SecurityContextHolder.clearContext();
        //return role;

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
