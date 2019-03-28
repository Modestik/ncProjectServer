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
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Контроллер возвращающий роль залогинившегося пользователя
     *
     * @param request запрос
     * @return role
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity getRole(HttpServletRequest request) {
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout";
    }
}
