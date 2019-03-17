package nc.test.service;

import nc.test.dao.interfaces.CustomerDao;
import nc.test.dao.interfaces.OperCustDao;
import nc.test.model.MutantOperCust;
import nc.test.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    public boolean updateUser(MutantOperCust mutantOperCust)
    {
        try {
            customerDao.update(mutantOperCust);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    public MutantOperCust  getUserByLogin(String username)
    {
        return customerDao.getCustomer(username);
    }


}
