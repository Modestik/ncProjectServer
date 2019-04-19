package nc.test.service;

import nc.test.model.Operator;
import org.springframework.http.HttpStatus;

public interface OperatorService {
    HttpStatus updateUser(Operator operator);

    Operator getUserByLogin(String username);
}
