package nc.test.controller;

import nc.test.model.Car;
import nc.test.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * Контроллер для добавления сотрудника
     *
     * @param jsonStr
     */
    @PostMapping()
    public ResponseEntity createCar(@Valid @RequestBody String jsonStr) {
        return ResponseEntity.status(carService.createCar(jsonStr)).build();
    }
}
