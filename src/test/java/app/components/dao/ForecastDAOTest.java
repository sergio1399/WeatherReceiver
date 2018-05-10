package app.components.dao;

import app.components.model.City;
import app.config.MyWebAppInitializer;
import app.config.PersistenceJPAConfig;
import app.config.RootConfig;
import app.config.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        (classes={WebConfig.class, PersistenceJPAConfig.class, RootConfig.class},
                      loader=AnnotationConfigContextLoader.class)
@Transactional
public class ForecastDAOTest {

    @Autowired
    private ForecastDAO dao;

    @Test
    public void testGetCity(){

        City city = dao.getCity("Boston");
        Assert.assertEquals(" MA", city.getRegion());
        Assert.assertEquals("United States", city.getCountry());
        Assert.assertEquals(new Integer(2367105), city.getId());
    }

}