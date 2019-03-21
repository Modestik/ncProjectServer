package nc.test.controller;

import nc.test.model.Car;
import nc.test.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/free")
    public List<Car> getFreeCars() {
        List<Car> carList = carService.getFreeCars();
        return carList;
    }
    @GetMapping("/all")
    public List<Car> getAllCars() {
        List<Car> carList = carService.getAllCars();
        return carList;
    }

    @PostMapping()
    public ResponseEntity createCar(@RequestBody Car car) {
        return ResponseEntity.status(carService.createCar(car)).build();
    }
}
