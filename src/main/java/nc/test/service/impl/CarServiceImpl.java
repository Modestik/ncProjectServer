package nc.test.service.impl;

import nc.test.dao.CarDao;
import nc.test.model.Car;
import nc.test.service.CarService;
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
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Override
    public HttpStatus createCar(Car car) {
        try {
            //Если пользователь уже есть в системе, выход
            if (carDao.carIsEmpty(car.getNumber())) {
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
