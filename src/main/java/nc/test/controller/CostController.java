package nc.test.controller;

import nc.test.model.PriceDto;
import nc.test.service.impl.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class CostController {

    @Autowired
    CostService costService;

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public ResponseEntity<Double> getPrice(@RequestBody PriceDto priceDto) {
        double result = 0;
        try {
            result = costService.getPrice(priceDto.getPointFrom(), priceDto.getPointTo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
