package nc.test.service;

import nc.test.model.Orders;
import nc.test.model.Point;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JSONParser {

    public Orders request(String jsonStr) throws JSONException {
        Orders orders = new Orders();
        JSONObject object = new JSONObject(jsonStr);

        orders.setPoint_from(object.get("point_from").toString());
        orders.setPoint_to(object.get("point_to").toString());
        orders.setWeight(object.get("weight").toString());
        return orders;
    }

    public Point response(JSONObject object) throws JSONException {
        Point point = new Point();
        String pos = object
                .getJSONObject("response")
                .getJSONObject("GeoObjectCollection")
                .getJSONArray("featureMember")
                .getJSONObject(0)
                .getJSONObject("GeoObject")
                .getJSONObject("Point")
                .getString("pos");

        String[] parts = pos.split(" ");
        point.setLatitude(parts[0]);
        point.setLongitude(parts[1]);
        return point;
    }


}
