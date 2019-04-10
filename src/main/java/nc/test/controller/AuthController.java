package nc.test.controller;

import nc.test.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Контроллер возвращающий роль залогинившегося пользователя
     */
    @RequestMapping(value = "/auth/role", method = RequestMethod.GET)
    public ResponseEntity getRole() {
        String responce = authService.getRole();
        return new ResponseEntity(responce, responce == "" ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    /**
     * Logout
     */
    @RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
    public String clear() {
        SecurityContextHolder.clearContext();
        return "logout";
    }
}
