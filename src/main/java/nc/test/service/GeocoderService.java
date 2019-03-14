package nc.test.service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class GeocoderService {

    private Geocoder geocoder;


    public GeocoderGeometry locationToCoordinate(String location) throws IOException {
        GeocoderGeometry coordinate = null;
        if (location != null && !location.isEmpty()) {
            GeocoderRequest request = new GeocoderRequest();
            request.setAddress(location);

            GeocodeResponse response = geocoder.geocode(request);
            if (response.getStatus() == GeocoderStatus.OK) {
                List<GeocoderResult> results = response.getResults();

                for (GeocoderResult result : results) {
                    GeocoderGeometry geometry = result.getGeometry();
                    coordinate = geometry;
                    break;
                }
            }
        }
        return coordinate;
    }

    public void getCoords(String location) throws IOException {
        GeocoderRequest request = new GeocoderRequestBuilder()
                .setAddress(location)
                .setLanguage("en").getGeocoderRequest();

        GeocodeResponse response = geocoder.geocode(request);

        if (response.getStatus().equals(GeocoderStatus.OK)) {
            List<GeocoderResult> results = response.getResults();
            for (GeocoderResult geoResult : results) {
                System.out.println(geoResult.getFormattedAddress());

                BigDecimal lat = geoResult.getGeometry().getLocation().getLat();
                BigDecimal lng = geoResult.getGeometry().getLocation().getLng();

                System.out.println("lat: " + lat);
                System.out.println("lng: " + lng);
            }
        }
    }


}
