package nc.test.service;

import nc.test.model.MutantOperCust;

public interface CustomerService {

    boolean updateUser(MutantOperCust mutantOperCust);
    MutantOperCust  getUserByLogin(String username);
}
