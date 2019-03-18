package nc.test.service;

import nc.test.dao.interfaces.CarDao;
import nc.test.model.Car;
import nc.test.model.Driver;
import nc.test.model.MutantOperCust;
import nc.test.model.Users;
import nc.test.service.interfaces.CarService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> getFreeCars() {
        return carDao.getFreeCars();
    }

    @Override
    public HttpStatus createCar(String jsonStr) {
        try {
            //Spring в @RequestBody не воспринимает JSONObject (получает пустое значение) поэтому конвектор через String
            JSONObject json = new JSONObject(jsonStr);
            //Если пользователь уже есть в системе, выход
            if (carDao.carIsEmpty(json.get("number").toString())) {
                Car car = new Car();
                car.setNumber(json.getString("number"));
                car.setModel(json.getString("model"));
                car.setColor(json.getString("color"));
                carDao.insert(car);
                return HttpStatus.CREATED;
            } else {
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
