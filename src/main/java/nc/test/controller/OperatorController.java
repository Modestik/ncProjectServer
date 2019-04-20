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

    @RequestMapping(value = "/operator/aboutme", method = RequestMethod.GET)
    public Operator getOperator() {
        Operator operator = operatorService.getUserByLogin();
        return operator;
    }

    @RequestMapping(value = "/operator/aboutme", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody Operator operator) {
        HttpStatus status = operatorService.updateUser(operator);
        return ResponseEntity.status(status).build();
    }
}
