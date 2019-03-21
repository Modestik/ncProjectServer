package nc.test.service.interfaces;

import nc.test.model.Car;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CarService {

    List<Car> getFreeCars();

    List<Car> getAllCars();

    HttpStatus createCar(Car car);
}
