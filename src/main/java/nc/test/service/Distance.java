package nc.test.service;

import nc.test.model.Point;
import org.springframework.stereotype.Service;

@Service
public class Distance {

    private double lat1, lon1, lat2, lon2, result;

    public Double distanceTo(Point p1, Point p2) {

        lat1 = Double.parseDouble(p1.getLatitude());
        lon1 = Double.parseDouble(p1.getLongitude());
        lat2 = Double.parseDouble(p2.getLatitude());
        lon2 = Double.parseDouble(p2.getLongitude());
        result = 111.2 * Math.sqrt( (lon1 - lon2)*(lon1 - lon2) + (lat1 - lat2)*Math.cos(Math.PI*lon1/180)*(lat1 - lat2)*Math.cos(Math.PI*lon1/180));

        return result;
    }

}
