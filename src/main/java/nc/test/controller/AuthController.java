package nc.test.controller;

import nc.test.model.Order;
import nc.test.model.Users;
import nc.test.service.OrderService;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String sayHi() {
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
       // System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        // Users user = userService.getUserByLogin( )
        return "hi";
    }

    @GetMapping("/role")
    public String getRole() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]);
       // Users user = userService.getUserByLogin( )
        return "hi";
    }

}
