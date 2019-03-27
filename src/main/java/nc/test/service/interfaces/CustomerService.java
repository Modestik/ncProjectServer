package nc.test.service.interfaces;

import nc.test.model.MutantOperCust;

public interface CustomerService {

    boolean updateUser(MutantOperCust mutantOperCust);

    MutantOperCust getUserByLogin(String username);
}
