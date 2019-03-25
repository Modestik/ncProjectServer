package nc.test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nc.test.model.PointItem;
import nc.test.model.PriceDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

//todo artem
//nc.test.service.GeocodeService#getCoordinates idea подсвечивает public.. обрати внимание..
//PointItem - можно сделать конструктор с двумя полями..
//restTemplate в методе nc.test.service.GeocodeService#getCoordinates нужно перенести на уровень класса.. в поле..
//следи чтобы код не вылезал за полосу справа..
//String geocode - переделать через string builder.. FRAGMENT_APIKEY, FRAGMENT_URL, FRAGMENT_RESULTS - переделать через string builder..
//mapper.readValue(response.getBody(), ObjectNode.class).findValues("pos"); - переписать через отельные переменные.. чтоб не было много точек в строке..
//jsonNodes - список может содержать более одного элемента.. но ты делаешь ограничение когда кидаешь get запрос в geocoder..
//добавь комменты про это..
//for (JsonNode node : jsonNodes) - перепиши через stream;

@Service
public class GeocodeService {

    private final static String FRAGMENT_APIKEY = "07aaccb9-d30b-458f-8c06-49710fbacfcd";
    private final static String FRAGMENT_URL = "https://geocode-maps.yandex.ru/1.x/?apikey=" + FRAGMENT_APIKEY + "&format=json&geocode=";
    private final static String FRAGMENT_RESULTS = "&results=1";

    public PointItem getCoordinates(String address) throws IOException {
        PointItem pointItem = new PointItem();
        RestTemplate restTemplate = new RestTemplate();
        String geocode = FRAGMENT_URL + address + FRAGMENT_RESULTS;
        ResponseEntity<String> response = restTemplate.exchange(geocode, HttpMethod.GET, null, String.class);

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
