package nc.test.controller;

import lombok.var;
import nc.test.exception.NotFoundException;
import nc.test.model.Users;
import nc.test.security.SecurityCheck;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Контроллер возвращающий роль залогинившегося пользователя
     *
     * @param request  запрос
     * @param response ответ
     * @return role
     * @throws IOException что бы вывести ошибку
     */
    @GetMapping("/role")
    public String getRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String basic = request.getHeader("Authorization");
        if (SecurityCheck.checkBasicAuth(basic))
            return userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRole();
        else {
            response.sendError(401);
            return null;
        }
    }

    /**
     * Контроллер создающий пользователя
     *
     * @param newUser пользователь которого надо создать
     */
    @PostMapping
    public void createUser(@Valid @RequestBody Users newUser, HttpServletResponse response) {
        //почти ооп
        try {
            userService.getUserByLogin(newUser.getUsername());
            response.setStatus(406);
        } catch (NotFoundException e) {
            userService.create(newUser);
            response.setStatus(201);
        }
    }

    /**
     * Logout
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/clear"})
    public String clear(HttpServletRequest request, HttpServletResponse response) {
       /* HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(javax.servlet.http.Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "logout";*/
       SecurityCheck.checkBasicAuth("");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout";
    }
}
