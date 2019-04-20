package nc.test.service.impl;

import nc.test.dao.OperatorDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Operator;
import nc.test.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private OperatorDao operatorDao;

    @Override
    public HttpStatus updateUser(Operator operator) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            operator.setUsername(username);
            operatorDao.update(operator);
            return HttpStatus.ACCEPTED;
        } catch (Exception ex) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public Operator getUserByLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        operatorDao.getOperator(username);
        Operator operator = operatorDao.getOperator(username).orElseThrow(() -> new NotFoundException(username));
        return operator;
    }
}
