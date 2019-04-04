package nc.test.controller;

import nc.test.model.Users;
import nc.test.security.TokenUtil;
import nc.test.service.AuthService;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auth/login", method = {RequestMethod.GET})
    public ResponseEntity authenticate() {
        try {
            //String username = authenticationRequest.getUsername();
            // String password = authenticationRequest.getPassword();

            //UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            //Authentication authentication = this.authenticationManager.authenticate(token);
            //SecurityContextHolder.getContext().setAuthentication(authentication);
            //UserDetails userDetails = this.userService.loadUserByUsername(username);
            Users user = userService.getUserByLogin(
                    ((UserDetails) SecurityContextHolder.getContext().
                    getAuthentication().getPrincipal()).getUsername());

            return new ResponseEntity(TokenUtil.createToken(user), HttpStatus.OK);

        } catch (BadCredentialsException bce) {
            return new ResponseEntity(null, HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

    }

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
