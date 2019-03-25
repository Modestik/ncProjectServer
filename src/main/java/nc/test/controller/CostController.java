package nc.test.controller;

import nc.test.model.PriceDto;
import nc.test.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CostController {

    @Autowired
    CostService costService;

    @RequestMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity getPrice(@RequestBody PriceDto priceDto) throws IOException {
        double result = costService.getPrice(priceDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
