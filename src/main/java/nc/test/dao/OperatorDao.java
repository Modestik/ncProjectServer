package nc.test.dao;

import nc.test.model.MutantOperCust;
import nc.test.model.Operator;

import java.util.List;

public interface OperatorDao {
    List<Operator> getAllOperators();

    void insert(Operator operator);

    void update(Operator operator);

    void deleteUserByLogin(String username);
}
