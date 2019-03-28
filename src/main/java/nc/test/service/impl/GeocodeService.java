package nc.test.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import nc.test.model.PointItem;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//todo artem
//+-for (JsonNode node : jsonNodes) - перепиши через stream;

@Slf4j
@Service
public class GeocodeService {

    private final static String HTTPS = "https://geocode-maps.yandex.ru/1.x/?apikey=";
    private final static String APIKEY = "07aaccb9-d30b-458f-8c06-49710fbacfcd";
    private final static String FORMAT = "&format=json&geocode=";
    private final static String RESULTS = "&results=1";
    private RestTemplate restTemplate = new RestTemplate();

    PointItem getCoordinates(String address) throws IOException {
        String geocode = getUrl(address);
        ResponseEntity<String> response = restTemplate.exchange(geocode, HttpMethod.GET, null, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNodes = mapper.readValue(response.getBody(), ObjectNode.class);
        List<JsonNode> position = jsonNodes.findValues("pos");
        Stream<JsonNode> stream = position.stream();
        JsonNode json = stream.iterator().next();
        String coords = json.asText();
        String[] latLon = coords.split(" ");
        return new PointItem(latLon[0], latLon[1]);
    }

    private String getUrl(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append(HTTPS)
                .append(APIKEY)
                .append(FORMAT)
                .append(address)
                .append(RESULTS)
                .toString();
    }

}
