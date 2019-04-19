package nc.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import nc.test.model.PointItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CostService {

    private static final double TARIFF = 18;

    @Autowired
    GeocodeService geocodeService;

    @Autowired
    DistanceService distanceService;

    public Map<String, Double> getPrice(String pointFrom, String pointTo) throws IOException {
        log.debug("pointFrom: {}", pointFrom);
        log.debug("pointTo: {}", pointTo);
        if (!pointFrom.isEmpty() || !pointTo.isEmpty()) {
            PointItem pointItem1 = geocodeService.getCoordinates(pointFrom);
            PointItem pointItem2 = geocodeService.getCoordinates(pointTo);
            double dis = distanceService.distanceTo(pointItem1, pointItem2);
            double price = calculate(dis);
            log.debug("Point: {}, {}", pointItem1.getLatitude(), pointItem1.getLongitude());
            log.debug("Point: {}, {}", pointItem2.getLatitude(), pointItem2.getLongitude());
            log.debug("Tariff: {}", TARIFF);
            log.debug("Distance: {} {}", dis, "km");
            log.debug("Price: {} {}", price, "rub");
            Map<String, Double> map = new HashMap<>();
            map.put("tariff", TARIFF);
            map.put("distance", dis);
            map.put("price", price);
            return map;
        } else {
            return new HashMap<>();
        }
    }

    private double calculate(Double distance) {
        return Math.round((distance * CostService.TARIFF) * 100.0) / 100.0;
    }
}
