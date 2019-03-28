package nc.test.dao;

import nc.test.model.MutantOperCust;


public interface CustomerDao {

    MutantOperCust getCustomer(String name);
    void update(MutantOperCust mutantOperCust);
}
