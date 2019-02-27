package nc.test.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("CUSTOMER")
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello world";
    }
}
