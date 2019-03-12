package nc.test.service;

import nc.test.dao.interfaces.CarDao;
import nc.test.model.Car;
import nc.test.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
