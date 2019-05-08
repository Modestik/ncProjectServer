package nc.test.controller;

import nc.test.model.Customer;
import nc.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer/aboutme", method = RequestMethod.GET)
    public Customer getUser() {
        Customer customer = customerService.getUserByLogin();
        return customer;
    }

    @RequestMapping(value = "/customer/aboutme", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody Customer customer) {
        HttpStatus status = customerService.updateUser(customer);
        return ResponseEntity.status(status).build();
    }
}
