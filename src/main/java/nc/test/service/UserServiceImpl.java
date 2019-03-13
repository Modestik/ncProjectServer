package nc.test.service;

import nc.test.dao.interfaces.DriverDao;
import nc.test.dao.interfaces.OperCustDao;
import nc.test.dao.interfaces.UserDao;
import nc.test.exception.NotFoundException;
import nc.test.model.Driver;
import nc.test.model.MutantOperCust;
import nc.test.model.Users;
import nc.test.service.interfaces.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DriverDao driverDao;
    @Autowired
    private OperCustDao operCustDao;

    @Override
    public Users getUserByLogin(String username) {
        return userDao.getUserByLogin(username).orElseThrow(() -> new NotFoundException(username));
    }

    /**
     * Метод создающий сотрудника
     *
     * @param jsonStr
     * @return код httpStatus
     */
    @Override
    public HttpStatus createUsers(String jsonStr) {
        try {
            //Spring в @RequestBody не воспринимает JSONObject (получает пустое значение) поэтому конвектор через String
            JSONObject json = new JSONObject(jsonStr);
            //Если пользователь уже есть в системе, выход
            if (userDao.loginIsEmpty(json.get("username").toString())) {
                Users user = new Users();
                user.setUsername(json.get("username").toString());
                user.setPassword(json.get("password").toString());
                user.setRole(json.get("role").toString());
                userDao.insert(user);
                //Добавим в отдельную таблицу
                if (user.getRole().equals("DRIVER")) {
                    Driver driver = new Driver();
                    driver.setUsername(json.get("username").toString());
                    driver.setFirstName(json.get("firstName").toString());
                    driver.setLastName(json.get("lastName").toString());
                    driver.setPhone(json.get("phone").toString());
                    driverDao.insert(driver);
                } else {
                    MutantOperCust mutantOperCust = new MutantOperCust();
                    mutantOperCust.setUsername(json.get("username").toString());
                    mutantOperCust.setFirstName(json.get("firstName").toString());
                    mutantOperCust.setLastName(json.get("lastName").toString());
                    mutantOperCust.setPhone(json.get("phone").toString());
                    mutantOperCust.setTable(user.getRole().equals("OPERATOR") ? "operators" : "customers");
                    operCustDao.insert(mutantOperCust);
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
     *
     * @param jsonStr
     */
    @Override
    public boolean updateUsers(String jsonStr) {
        try {
            //Spring в @RequestBody не воспринимает JSONObject (получает пустое значение) поэтому конвектор через String
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                String role = json.get("role").toString();
                switch (role) {
                    case "DRIVER":
                        Driver driver = new Driver();
                        driver.setUsername(json.get("username").toString());
                        driver.setFirstName(json.get("firstName").toString());
                        driver.setLastName(json.get("lastName").toString());
                        driver.setPhone(json.get("phone").toString());
                        driver.setCarNumber(json.get("carNumber").toString());
                        driverDao.update(driver);
                        break;
                    case "OPERATOR":
                        MutantOperCust mutantOperCust = new MutantOperCust();
                        mutantOperCust.setUsername(json.get("username").toString());
                        mutantOperCust.setFirstName(json.get("firstName").toString());
                        mutantOperCust.setLastName(json.get("lastName").toString());
                        mutantOperCust.setPhone(json.get("phone").toString());
                        mutantOperCust.setTable("operators");
                        operCustDao.update(mutantOperCust);
                        break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteUserByLogin(String jsonStr) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            String username = json.getString("username");
            Users user = userDao.getUserByLogin(username).get();
            userDao.deleteUserByLogin(username);
            if (user.getRole().equals("DRIVER"))
                driverDao.deleteUserByLogin(username);
            if (user.getRole().equals("OPERATOR"))
                operCustDao.deleteUserByLogin(username);
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
    public String getAllEmployees() {
        try {
            List<Driver> driverList = driverDao.getAllDrivers();
            List<MutantOperCust> mutantOperCustList = operCustDao.getAllOperators();

            JSONArray jsonArray = new JSONArray();
            for (Driver driver : driverList
            ) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", driver.getUsername());
                jsonObject.put("firstName", driver.getFirstName());
                jsonObject.put("lastName", driver.getLastName());
                jsonObject.put("phone", driver.getPhone());
                jsonObject.put("carNumber", driver.getCarNumber());
                jsonObject.put("role", "DRIVER");
                jsonArray.put(jsonObject);
            }
            for (MutantOperCust mutantOperCust : mutantOperCustList
            ) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", mutantOperCust.getUsername());
                jsonObject.put("firstName", mutantOperCust.getFirstName());
                jsonObject.put("lastName", mutantOperCust.getLastName());
                jsonObject.put("phone", mutantOperCust.getPhone());
                jsonObject.put("role", "OPERATOR");
                jsonArray.put(jsonObject);
            }
            return jsonArray.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
