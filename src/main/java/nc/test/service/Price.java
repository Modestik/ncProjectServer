package nc.test.service;

import org.springframework.stereotype.Service;

@Service
public class Price {

    private double result;

    public Double calculate(Double distance, Double coefficient) {
        result = Math.round((distance * coefficient)*100)/100;
        return result;
    }

}
