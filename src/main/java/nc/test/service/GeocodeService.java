package nc.test.service;

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
import java.util.stream.Stream;

//todo artem
//+PointItem - можно сделать конструктор с двумя полями..
//+restTemplate в методе nc.test.service.GeocodeService#getCoordinates нужно перенести на уровень класса.. в поле..
//+следи чтобы код не вылезал за полосу справа..
//+String geocode - переделать через string builder.. FRAGMENT_APIKEY, FRAGMENT_URL, FRAGMENT_RESULTS - переделать через string builder..
//+mapper.readValue(response.getBody(), ObjectNode.class).findValues("pos"); - переписать через отельные переменные.. чтоб не было много точек в строке..
//?jsonNodes - список может содержать более одного элемента.. но ты делаешь ограничение когда кидаешь get запрос в geocoder..
//добавь комменты про это..
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
        PointItem pointItem = new PointItem(latLon[0], latLon[1]);

        return pointItem;
    }

    private String getUrl(String address) {
        String[] url = new String[]{
                HTTPS,
                APIKEY,
                FORMAT,
                address,
                RESULTS
        };
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : url) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

}
