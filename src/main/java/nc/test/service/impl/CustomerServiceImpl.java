package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.dao.CustomerDao;
import nc.test.model.Customer;
import nc.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public HttpStatus updateUser(Customer customer) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            customer.setUsername(username);
            customerDao.update(customer);
            log.info("Информация для customer обновлена");
            return HttpStatus.OK;
        } catch (Exception ex) {
            log.error("При обновлении информации для customer что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }

    public Customer getUserByLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return customerDao.getCustomer(username);
    }


}
