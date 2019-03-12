package nc.test.dao.interfaces;

import nc.test.model.MutantOperCust;

import java.util.List;

public interface OperCustDao {
    List<MutantOperCust> getAllOperators();
    void insert(MutantOperCust mutantOperCust);
    void update(MutantOperCust mutantOperCust);
}
