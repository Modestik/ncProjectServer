package nc.test.controller;

import nc.test.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Контроллер возвращающий роль залогинившегося пользователя
     *
     * @return role
     */
    @RequestMapping(value = "/auth/role", method = RequestMethod.GET)
    public ResponseEntity getRole() {
        String responce = authService.getRole();
        return new ResponseEntity(responce, responce == "" ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }


    /**
     * Logout
     *
     * @return
     */
    @RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
    public String clear() {
        SecurityContextHolder.clearContext();
        return "logout";
    }
}
