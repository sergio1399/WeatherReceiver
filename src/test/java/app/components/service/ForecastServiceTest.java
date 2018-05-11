package app.components.service;

import app.components.model.Forecast;
import app.components.view.ForecastCityView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ForecastServiceTest {

    @Mock
    private ForecastService service;

    private static SimpleDateFormat RFC822DATEFORMAT
            = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa Z", Locale.US);

    @Test
    public void testGetForecast() throws ParseException {
        ForecastCityView view = new ForecastCityView.Builder("Boston").cityRegion(" MA").cityCountry("United States").
                temperature("48").wind("chill: 48, direction:0, speed:4").text("Partly Cloudy").pressure(1021.0).
                visibility(12.5).cityId(2367105).build();
        org.mockito.Mockito.when(service.getForecast("Boston")).thenReturn(view);
        ForecastCityView otherView = new ForecastCityView.Builder("Boston").cityRegion(" MA").cityCountry("United States").
                temperature("48").wind("chill: 48, direction:0, speed:4").text("Partly Cloudy").pressure(1021.0).
                visibility(12.5).cityId(2367105).build();
        assertEquals(service.getForecast("Boston"), otherView);
    }


}