package nc.test.controller;

import nc.test.model.PriceDto;
import nc.test.service.impl.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CostController {

    @Autowired
    CostService costService;

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public ResponseEntity<Map> getPrice(@RequestBody PriceDto priceDto) {
        Map result = new HashMap<>();
        try {
            result = costService.getPrice(priceDto.getPointFrom(), priceDto.getPointTo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
