package nc.test.controller;

import nc.test.model.Car;
import nc.test.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllDrivers() {
        List<Car> carList = carService.getFreeCars();
        return carList;
    }
}
