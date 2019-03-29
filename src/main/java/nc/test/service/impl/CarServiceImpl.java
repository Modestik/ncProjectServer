package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.dao.CarDao;
import nc.test.model.Car;
import nc.test.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> getFreeCars() {

        List<Car> list = carDao.getFreeCars();

        log.info(new StringBuilder()
                .append("Получена список из свободных машин состоящий из ")
                .append(list.size())
                .append(" машин ")
        );

        return list;
    }

    @Override
    public List<Car> getAllCars() {

        List<Car> list = carDao.getAllCars();

        log.info(new StringBuilder()
                .append("Получена список из свободных машин состоящий из ")
                .append(list.size())
                .append(" машин ")
        );

        return list;
    }

    @Override
    public HttpStatus createCar(Car car) {
        try {
            if (carDao.carIsEmpty(car.getNumber())) {
                carDao.insert(car);
                log.info("Машина добавлена");
                return HttpStatus.CREATED;
            } else {
                log.error("Машина с таким номером уже есть");
                return HttpStatus.NOT_ACCEPTABLE;
            }
        } catch (Exception e) {
            log.error("При добавлении машины что-то пошло не так...");
            return HttpStatus.BAD_REQUEST;
        }
    }
}
