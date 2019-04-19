package nc.test.controller;

import nc.test.model.Operator;
import nc.test.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperatorController {

    @Autowired
    OperatorService operatorService;

    @RequestMapping(value = "/operator/name", method = RequestMethod.GET)
    public Operator getOperator(@RequestParam("name") String name) {
        Operator operator = operatorService.getUserByLogin(name);
        return operator;
    }

    @RequestMapping(value = "/operator/name", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody Operator operator) {
        HttpStatus status = operatorService.updateUser(operator);
        return ResponseEntity.status(status).build();
    }
}
