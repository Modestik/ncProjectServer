package nc.test.service;

import nc.test.model.Customer;
import org.springframework.http.HttpStatus;

public interface CustomerService {

    HttpStatus updateUser(Customer customer);

    Customer getUserByLogin(String username);
}
