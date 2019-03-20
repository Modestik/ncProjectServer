package nc.test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nc.test.model.PointItem;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class GeocodeService {

    public PointItem getCoordinates(String url) throws IOException {
        PointItem pointItem = new PointItem();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> jsonNodes = mapper.readValue(response.getBody(), ObjectNode.class).findValues("pos");
        for (JsonNode node : jsonNodes) {
            String coords = node.asText();
            String[] parts = coords.split(" ");
            pointItem.setLatitude(parts[0]);
            pointItem.setLongitude(parts[1]);
        }
        return pointItem;
    }

}
