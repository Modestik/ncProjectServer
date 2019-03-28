package nc.test.controller;

import nc.test.model.Car;
import nc.test.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/free", method = RequestMethod.GET)
    public List<Car> getFreeCars() {
        List<Car> carList = carService.getFreeCars();
        return carList;
    }

    @RequestMapping(value = "/car/all", method = RequestMethod.GET)
    public List<Car> getAllCars() {
        List<Car> carList = carService.getAllCars();
        return carList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createCar(@RequestBody Car car) {
        return ResponseEntity.status(carService.createCar(car)).build();
    }
}
