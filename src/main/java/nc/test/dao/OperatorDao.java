package nc.test.dao;

import nc.test.model.Operator;

import java.util.List;
import java.util.Optional;

public interface OperatorDao {

    List<Operator> getAllOperators();

    void insert(Operator operator);

    void update(Operator operator);

    void deleteUserByLogin(String username);

    Optional<Operator> getOperator(String name);
}
