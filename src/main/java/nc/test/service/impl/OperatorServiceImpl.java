package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.dao.OperatorDao;
import nc.test.model.Operator;
import nc.test.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private OperatorDao operatorDao;

    @Override
    public HttpStatus updateUser(Operator operator) {
        try {
            operatorDao.update(operator);
            log.info("Информация для operator обновлена");
            return HttpStatus.OK;
        } catch (Exception ex) {
            log.error("При обновлении информации для operator что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public Operator getUserByLogin(String username) {
        return operatorDao.getOperator(username);
    }
}
