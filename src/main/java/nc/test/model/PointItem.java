package nc.test.model;

import lombok.Data;

@Data
public class PointItem {

    private String latitude;
    private String longitude;

    public PointItem(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
