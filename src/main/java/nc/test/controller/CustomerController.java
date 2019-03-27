package nc.test.controller;


import nc.test.model.MutantOperCust;
import nc.test.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public MutantOperCust getUser(@RequestParam("name") String name) {
        return customerService.getUserByLogin(name);
    }


    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public ResponseEntity updateUser(@Valid @RequestBody MutantOperCust mutantOperCust) {
        return customerService.updateUser(mutantOperCust) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
