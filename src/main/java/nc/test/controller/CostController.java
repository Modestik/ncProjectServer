package nc.test.controller;

import nc.test.model.Orders;
import nc.test.model.Point;
import nc.test.service.Distance;
import nc.test.service.JSONReader;
import nc.test.service.JSONParser;
import nc.test.service.Price;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

//todo artem
//использовать ctrl+alt+l - автоформатирование кода
//@RestController() - удалить '()'
//@RequestMapping перенести над методом + почитать про produces + добавить method = RequestMethod.POST прям в анноташку
//почему RequestMethod.POST а не get ? может get более логично..
//переименовать nc.test.controller.CostController#saveData
//что за анноташка @Valid ? нужна нам ?
//@RequestBody String jsonStr - принимать не string .. а объект класса..
//создать класс (будет содержать параметры которые приходят в метод - address1, address2, weight) - PriceRequest, PriceDto, PriceTo, PriceEntity.. подумай..
//Orders orders = jsonParser.request(jsonStr) - это будет объект твоего нового класса.. json parser убрать..
//переименовать поля тут nc.test.model.Orders - id_order to idOrder.. shift+f6 - переименует во всех местах в коде..
//apikey, fpart_url, tpart_url - сделать статическими.. first-part-url - не лучшее название.. сам url делиться на части.. почитай и назови хорошо..
//final static поля нужно называть вот так APIKEY, FPART_URL, TPART_URL.. почитай про camel case..
//String url_response1 = fpart_url + orders.getPoint_from() + tpart_url; url_response1 - не хороший формат.. нужен urlResponse1..
//но и не очень понятное название переменной.. geocode1, geocode2 лучше..
//jsonParser, jsonReader - убрать.. добавить resttemplate.. request для координат кидать через него..

@RestController()
@RequestMapping(value = "/cost", produces = MediaType.APPLICATION_JSON_VALUE)
public class CostController {

    @Autowired
    JSONParser jsonParser;

    @Autowired
    JSONReader jsonReader;

    @Autowired
    Distance distance;

    @Autowired
    Price price;

    private final String apikey = "07aaccb9-d30b-458f-8c06-49710fbacfcd";
    private final String fpart_url = "https://geocode-maps.yandex.ru/1.x/?apikey="+ apikey +"&format=json&geocode=";
    private final String tpart_url = "&results=1";
    //https://geocode-maps.yandex.ru/1.x/?apikey=07aaccb9-d30b-458f-8c06-49710fbacfcd&format=json&geocode=Russia, Voronezh, Taneeva, 10&results=1

    @PostMapping()
    public ResponseEntity saveData(@Valid @RequestBody String jsonStr) throws JSONException, IOException {
        Orders orders = jsonParser.request(jsonStr);
        //JSONObject object = jsonReader.read(jsonStr);
        //Orders orders = jsonParser.request(object);
        String url_response1 = fpart_url+orders.getPoint_from()+tpart_url;
        String url_response2 = fpart_url+orders.getPoint_to()+tpart_url;
        JSONObject object1 = jsonReader.read(url_response1);
        JSONObject object2 = jsonReader.read(url_response2);


        Point point1 = jsonParser.response(object1);
        Point point2 = jsonParser.response(object2);

        //System.out.println("Point 1: " + point1.getLatitude() + " " + point1.getLongitude());
        //System.out.println("Point 2: " + point2.getLatitude() + " " + point2.getLongitude());
        //System.out.println("Weight: " + orders.getWeight());

        double dis = distance.distanceTo(point1, point2);
        //System.out.println("Distance: " + dis + " km");
        double pr = price.calculate(dis, Double.parseDouble(orders.getWeight()));
        //System.out.println("Price: " + pr + " rub");

        return new ResponseEntity(pr, HttpStatus.OK);
    }

}
