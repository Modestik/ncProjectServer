package nc.test.controller;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RestController()
@RequestMapping(value = "/cost", produces = MediaType.APPLICATION_JSON_VALUE)
public class CostController {

    @PostMapping
    public void test(HttpServletRequest request, HttpServletResponse response) {
        try
        {
            /*GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyDPZl5-8ItpQEJ2jPc4luuFrfiyFVx0de4")
                    .queryRateLimit(1)
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context, "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //System.out.println(gson.toJson(results[0].addressComponents));


            String lat = gson.toJson(results[0].geometry.location.lat);
            String lng = gson.toJson(results[0].geometry.location.lng);
            System.out.println("[" + lat + ", " + lng + "]");*/


            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Exception e)
        {
            System.out.println("** Exception: " + e);
        }
    }

}
