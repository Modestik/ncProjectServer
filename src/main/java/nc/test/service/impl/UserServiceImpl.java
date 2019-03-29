package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.dao.CustomerDao;
import nc.test.dao.DriverDao;
import nc.test.dao.OperatorDao;
import nc.test.dao.UserDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Driver;
import nc.test.model.Customer;
import nc.test.model.Operator;
import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DriverDao driverDao;
    @Autowired
    private OperatorDao operatorDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Users getUserByLogin(String username) {
        return userDao.getUserByLogin(username).orElseThrow(() -> new NotFoundException(username));
    }

    /**
     * Метод создающий сотрудника
     *
     * @return код httpStatus
     */
    @Override
    @Transactional
    public HttpStatus createEmployees(Users users) {
        try {
            //Если пользователь уже есть в системе, выход
            if (userDao.loginIsEmpty(users.getUsername())) {
                userDao.insert(users);
                //Добавим в отдельную таблицу
                if (users.getRole().equals("DRIVER")) {
                    Driver driver = users.toDriver();
                    driverDao.insert(driver);
                } else if (users.getRole().equals("OPERATOR")) {
                    Operator operator = users.toOperator();
                    operatorDao.insert(operator);
                }
                log.info("Сотрудник добавлен");
                return HttpStatus.CREATED;
            } else {
                log.error("Сотрудник с таким именем уже есть");
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } catch (Exception e) {
            log.error("При добавлении сотрудника что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    @Transactional
    public HttpStatus createCustomers(Users users) {
        try {
            if (userDao.loginIsEmpty(users.getUsername())) {
                userDao.insert(users);
                Customer cust = users.toCustomer();
                customerDao.insert(cust);
                log.info("Customer добавлен");
                return HttpStatus.CREATED;
            } else {
                log.error("Customer с таким именем уже есть");
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } catch (Exception e) {
            log.error("При добавлении Customer что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * Метод update для всех сотрудников(после кнопки update на странице Админа)
     */
    @Override
    @Transactional
    public HttpStatus updateEmployees(Users[] users) {
        try {
            for (int i = 0; i < users.length; i++) {
                String role = users[i].getRole();
                switch (role) {
                    case "DRIVER":
                        Driver driver = users[i].toDriver();
                        driverDao.update(driver);
                        break;
                    case "OPERATOR":
                        Operator operator = users[i].toOperator();
                        operatorDao.update(operator);
                        break;
                }
            }
            log.info("Информация для сотрудника обновлена");
            return HttpStatus.OK;
        } catch (Exception e) {
            log.error("При обновлении информации для сотрудника что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    @Transactional
    public HttpStatus deleteUserByLogin(String username) {
        try {
            Users user = userDao.getUserByLogin(username).get();
            userDao.deleteUserByLogin(username);
            if (user.getRole().equals("DRIVER"))
                driverDao.deleteUserByLogin(username);
            if (user.getRole().equals("OPERATOR"))
                operatorDao.deleteUserByLogin(username);
            log.info("Удаление сотрудника прошло успешно");
            return HttpStatus.OK;
        } catch (Exception e) {
            log.error("При удалении что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * Метод возвращающий всех сотрудников
     *
     * @return
     */
    @Override
    @Transactional
    public List<Users> getAllEmployees() {
        try {
            List<Driver> driverList = driverDao.getAllDrivers();
            List<Operator> operatorList = operatorDao.getAllOperators();

            List<Users> userList = new ArrayList<>();

            for (Driver driver : driverList
            ) {
                userList.add(driver.toUser());
            }
            for (Operator operator : operatorList
            ) {
                userList.add(operator.toUser());
            }

            log.info(new StringBuilder()
                    .append("Получена список всех сотрудников состоящий из ")
                    .append(userList.size())
                    .append(" человек")
            );

            return userList;
        } catch (Exception e) {
            return null;
        }
    }
}
