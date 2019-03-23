package nc.test.service;

import lombok.extern.slf4j.Slf4j;
import nc.test.model.PointItem;
import nc.test.model.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CostService {

    @Autowired
    GeocodeService geocodeService;

    @Autowired
    DistanceService distanceService;

    public double getPrice(PriceDto priceDto) throws IOException {

        PointItem pointItem1 = geocodeService.getCoordinates(priceDto.getAddress1());
        PointItem pointItem2 = geocodeService.getCoordinates(priceDto.getAddress2());
        double dis = distanceService.distanceTo(pointItem1, pointItem2);
        double price = calculate(dis, Double.parseDouble(priceDto.getTariff()));

        log.debug("Point: {}, {}", pointItem1.getLatitude(), pointItem1.getLongitude());
        log.debug("Point: {}, {}", pointItem2.getLatitude(), pointItem2.getLongitude());
        log.debug("Tariff: {}", priceDto.getTariff());
        log.debug("Distance: {} {}", dis, "km");
        log.debug("Price: {} {}", price, "rub");

        return price;
    }

    public long calculate(Double distance, Double coefficient) {
        return Math.round((distance * coefficient) * 100) / 100;
    }
}
