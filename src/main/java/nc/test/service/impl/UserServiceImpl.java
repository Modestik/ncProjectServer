package nc.test.service.impl;
import nc.test.dao.CustomerDao;
import nc.test.dao.DriverDao;
import nc.test.dao.OperatorDao;
import nc.test.dao.UserDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Driver;
import nc.test.model.MutantOperCust;
import nc.test.model.Operator;
import nc.test.model.Users;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    @Override
    public HttpStatus createUsers(Users users) {
        try {
            //Если пользователь уже есть в системе, выход
            if (userDao.loginIsEmpty(users.getUsername())) {
                userDao.insert(users);
                //Добавим в отдельную таблицу
                if (users.getRole().equals("DRIVER")) {
                    Driver driver = users.toDriver();
                    driverDao.insert(driver);
                }
                if (users.getRole().equals("OPERATOR")) {
                    Operator operator = users.toOperator();
                    operatorDao.insert(operator);
                } else {
                    MutantOperCust cust = users.toCustomer();
                    customerDao.insert(cust);
                }
                return HttpStatus.CREATED;
            } else {
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * Метод update для всех юзеров(после кнопки update на странице Админа)
     * Мб потом добавится для Кастомера
     */
    @Override
    @Transactional
    public boolean updateUsers(Users[] users) {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteUserByLogin(String username) {
        try {
            Users user = userDao.getUserByLogin(username).get();
            userDao.deleteUserByLogin(username);
            if (user.getRole().equals("DRIVER"))
                driverDao.deleteUserByLogin(username);
            if (user.getRole().equals("OPERATOR"))
                operatorDao.deleteUserByLogin(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Метод возвращаю всех сотрудников(Метод возвращаю всех сотрудников)
     *
     * @return
     */
    @Override
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
            return userList;
        } catch (Exception e) {
            return null;
        }
    }
}
