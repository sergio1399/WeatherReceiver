package app.components.dao;

import app.components.model.City;
import app.components.model.Forecast;
import app.config.PersistenceJPAConfig;
import app.config.RootConfig;
import app.config.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (classes={PersistenceJPAConfig.class, RootConfig.class},
                      loader=AnnotationConfigContextLoader.class)
@Transactional(transactionManager = "transactionManager")
@Rollback
public class ForecastDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ForecastDAO dao;

    private static SimpleDateFormat RFC822DATEFORMAT
            = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm aaa Z", Locale.US);

    @Test
    public void testGetCity(){

        City city = dao.getCity("Boston");
        Assert.assertEquals(" MA", city.getRegion());
        Assert.assertEquals("United States", city.getCountry());
        Assert.assertEquals(new Integer(2367105), city.getId());
    }

    @Test
    public void  testGetForecast(){

        Forecast forecast = dao.getForecast("Boston");
        Assert.assertEquals("48", forecast.getTemperature());
        Assert.assertEquals("Partly Cloudy", forecast.getText());
    }

    @Test
    public void testSave() throws ParseException {

        City city = new City(1098081, "Perth", " WA", "Australia");
        Forecast forecast = new Forecast("79","chill: 79, direction:68, speed:11", "Sunny", 1009.0, 16.1,
                RFC822DATEFORMAT.parse("Fri, 11 May 2018 04:00 PM AWST") );
        forecast.setCity(city);
        dao.saveCityAndForecast(city, forecast);

        City savedCity = dao.getCity("Perth");
        Assert.assertEquals(" WA", savedCity.getRegion());
        Assert.assertEquals("Australia", savedCity.getCountry());
    }

}