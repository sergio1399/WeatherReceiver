package app.components.util;

import app.components.model.Forecast;
import app.components.view.ForecastCityView;
import org.json.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

public class ForecastConverterTest {

    private static SimpleDateFormat RFC822DATEFORMAT
            = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa Z", Locale.US);

    @Test
    public void testJsonToForecast() throws ParseException {
        JSONObject json = testObject();

        Forecast forecast = ForecastConverter.jsonToForecast(json);

        Forecast checkable = new Forecast("79", "chill: 79, direction:68, speed:11", "Sunny",
                1009.0, 16.1, RFC822DATEFORMAT.parse("Fri, 11 May 2018 04:00 PM AWST")  );

        assertEquals(checkable.getWind(), forecast.getWind());
        assertEquals(checkable.getText(), forecast.getText());
        assertEquals(checkable.getTemperature(), forecast.getTemperature());
    }

    @Test
    public void testJsonToForecastCityView() throws ParseException {
        JSONObject json = testObject();
        JSONObject location = new JSONObject();
        location.put("city", "Boston");
        location.put("country", "United States");
        location.put("region", " MA");
        json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").put("location", location);
        json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").put("link", "http://us.rd.yahoo.com/dailynews/city-1098081/");
        ForecastCityView view = ForecastConverter.jsonToForecastCityView(json);

        ForecastCityView checkable =  new ForecastCityView.Builder("Boston").cityRegion(" MA").cityCountry("United States").
                temperature("48").wind("chill: 79, direction:68, speed:11").text("Sunny").pressure(1021.0).
                visibility(12.5).cityId(2367105).build();

        assertEquals(checkable.cityName, view.cityName);
        assertEquals(checkable.text, view.text);
        assertEquals(checkable.wind, view.wind);

    }

    private JSONObject testObject(){
        JSONObject json = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject results = new JSONObject();
        JSONObject channel = new JSONObject();
        JSONObject atmo = new JSONObject();
        JSONObject item = new JSONObject();
        JSONObject cond = new JSONObject();
        atmo.put("pressure",  String.valueOf(1009.0));
        atmo.put("visibility", String.valueOf(16.1));
        cond.put("date", "Fri, 11 May 2018 04:00 PM AWST");
        cond.put("temp", "79");
        cond.put("text", "Sunny");
        item.put("condition", cond);
        channel.put("item", item);
        channel.put("atmosphere", atmo);
        JSONObject wind = new JSONObject();
        wind.put("chill", "79");
        wind.put("direction", "68");
        wind.put("speed", "11");
        channel.put("wind", wind);
        results.put("channel", channel);
        query.put("results", results);
        json.put("query", query);
        return json;
    }

}