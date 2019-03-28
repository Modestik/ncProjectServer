package nc.test.controller;

import lombok.extern.slf4j.Slf4j;
import nc.test.security.SecurityCheck;
import nc.test.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Security;

@Slf4j
@RestController
public class AuthController {


    @Autowired
    private AuthService authService;


    /**
     * Контроллер возвращающий роль залогинившегося пользователя
     *
     * @param request запрос
     * @return role
     */
    @RequestMapping(value = "/auth/role", method = RequestMethod.GET)
    public ResponseEntity getRole(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        log.info(httpSession.getId());
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());


        String responce = authService.getRole(request.getHeader("Authorization"));
        return new ResponseEntity(responce, responce == "" ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }


    /**
     * Logout (пока так)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/auth/clear")
    public String clear(HttpServletRequest request, HttpServletResponse response) {
        //SecurityCheck.checkBasicAuth("");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout";
    }
}
