package nc.test.controller;


import nc.test.model.MutantOperCust;
import nc.test.service.interfaces.CustomerService;
import nc.test.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/name")  //получить по username
    public MutantOperCust getUser(@RequestParam("name") String name) {
        return customerService.getUserByLogin(name);
    }

    @PostMapping("/name") //uodate user
    public ResponseEntity updateUser(@Valid @RequestBody MutantOperCust mutantOperCust) {
        return customerService.updateUser(mutantOperCust) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
