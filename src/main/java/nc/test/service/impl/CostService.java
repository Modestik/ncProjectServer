package nc.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import nc.test.model.PointItem;
import nc.test.model.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CostService {

    private static final int TARIFF = 18;

    @Autowired
    GeocodeService geocodeService;

    @Autowired
    DistanceService distanceService;

    public double getPrice(String pointFrom, String pointTo) throws IOException {
        log.debug("pointFrom: {}", pointFrom);
        log.debug("pointTo: {}", pointTo);
        if (!pointFrom.isEmpty() || !pointTo.isEmpty()) {
            PointItem pointItem1 = geocodeService.getCoordinates(pointFrom);
            PointItem pointItem2 = geocodeService.getCoordinates(pointTo);
            double dis = distanceService.distanceTo(pointItem1, pointItem2);
            double price = calculate(dis, TARIFF);
            log.debug("Point: {}, {}", pointItem1.getLatitude(), pointItem1.getLongitude());
            log.debug("Point: {}, {}", pointItem2.getLatitude(), pointItem2.getLongitude());
            log.debug("Tariff: {}", TARIFF);
            log.debug("Distance: {} {}", dis, "km");
            log.debug("Price: {} {}", price, "rub");
            return price;
        } else {
            return 0;
        }
    }

    private long calculate(Double distance, int coefficient) {
        return Math.round((distance * coefficient) * 100) / 100;
    }
}
