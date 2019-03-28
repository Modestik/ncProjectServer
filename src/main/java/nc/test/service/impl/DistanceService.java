package nc.test.service.impl;

import nc.test.model.PointItem;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    public Double distanceTo(PointItem p1, PointItem p2) {

        double lat1 = Double.parseDouble(p1.getLatitude());
        double lon1 = Double.parseDouble(p1.getLongitude());
        double lat2 = Double.parseDouble(p2.getLatitude());
        double lon2 = Double.parseDouble(p2.getLongitude());

        return 111.2
                * Math.sqrt((lon1 - lon2) * (lon1 - lon2) + (lat1 - lat2)
                * Math.cos(Math.PI * lon1 / 180) * (lat1 - lat2)
                * Math.cos(Math.PI * lon1 / 180));
    }

}
