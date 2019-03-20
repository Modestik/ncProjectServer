package nc.test.controller;

import lombok.extern.slf4j.Slf4j;
import nc.test.model.PriceDto;
import nc.test.model.PointItem;
import nc.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//todo artem
//+использовать ctrl+alt+l - автоформатирование кода
//+@RestController() - удалить '()'
//+?@RequestMapping перенести над методом + почитать про produces + добавить method = RequestMethod.POST прям в анноташку
//+?почему RequestMethod.POST а не get ? может get более логично..
//+переименовать nc.test.controller.CostController#saveData
//+что за анноташка @Valid ? нужна нам ?
//+@RequestBody String jsonStr - принимать не string .. а объект класса..
//+создать класс (будет содержать параметры которые приходят в метод - address1, address2, tariff) - PriceRequest, PriceDto, PriceTo, PriceEntity.. подумай..
//+Orders orders = jsonParser.request(jsonStr) - это будет объект твоего нового класса.. json parser убрать..
//+переименовать поля тут nc.test.model.Orders - idOrder to idOrder.. shift+f6 - переименует во всех местах в коде..
//+apikey, fpart_url, tpart_url - сделать статическими.. first-part-url - не лучшее название.. сам url делиться на части.. почитай и назови хорошо..
//+final static поля нужно называть вот так APIKEY, FPART_URL, TPART_URL.. почитай про camel case..
//+String url_response1 = fpart_url + orders.getPointFrom() + tpart_url; url_response1 - не хороший формат.. нужен urlResponse1..
//+но и не очень понятное название переменной.. geocode1, geocode2 лучше..
//+jsonParser, jsonReader - убрать.. добавить resttemplate.. request для координат кидать через него..

@RestController
@Slf4j
public class CostController {

    @Autowired
    DistanceService distanceService;

    @Autowired
    PriceService priceService;

    @Autowired
    GeocodeService geocodeService;

    private final static String FRAGMENT_APIKEY = "07aaccb9-d30b-458f-8c06-49710fbacfcd";
    private final static String FRAGMENT_URL = "https://geocode-maps.yandex.ru/1.x/?apikey=" + FRAGMENT_APIKEY + "&format=json&geocode=";
    private final static String FRAGMENT_RESULTS = "&results=1";

    @RequestMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity getPrice(@RequestBody PriceDto priceDto) throws IOException {

        String geocode1 = FRAGMENT_URL + priceDto.getAddress1() + FRAGMENT_RESULTS;
        String geocode2 = FRAGMENT_URL + priceDto.getAddress2() + FRAGMENT_RESULTS;
        PointItem pointItem1 = geocodeService.getCoordinates(geocode1);
        PointItem pointItem2 = geocodeService.getCoordinates(geocode2);
        log.debug("Point 1: " + pointItem1.getLatitude() + " " + pointItem1.getLongitude());
        log.debug("Point 2: " + pointItem2.getLatitude() + " " + pointItem2.getLongitude());
        log.debug("Tariff: " + priceDto.getTariff());
        double dis = distanceService.distanceTo(pointItem1, pointItem2);
        log.debug("Distance: " + dis + " km");
        double pr = priceService.getPrice(dis, Double.parseDouble(priceDto.getTariff()));
        log.debug("Price: " + pr + " rub");

        return new ResponseEntity(pr, HttpStatus.OK);
    }

}
