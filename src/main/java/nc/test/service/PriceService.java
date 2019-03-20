package nc.test.service;

import org.springframework.stereotype.Service;

@Service
public class PriceService {

    public long getPrice(Double distance, Double coefficient) {
        return Math.round((distance * coefficient) * 100) / 100;
    }

}
