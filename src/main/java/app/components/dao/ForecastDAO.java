package app.components.dao;

import app.components.exception.CityNotFoundException;
import app.components.exception.ForecastNotFoundException;
import app.components.model.City;
import app.components.model.Forecast;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ForecastDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveCityAndForecast(City city, Forecast forecast){
        City oldCity = entityManager.find(City.class, city.getId());
        if(oldCity == null) {
            entityManager.persist(city);
        }
        city = entityManager.find(City.class, city.getId());
        forecast.setCity(city);
        entityManager.persist(forecast);
    }

    public City getCity(String name){
        List<City> result = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);

        Root<City> cityRoot = criteria.from(City.class);
        criteria.select(cityRoot).where(builder.equal(cityRoot.get("name"), name));
        TypedQuery<City> query = entityManager.createQuery(criteria);
        result = query.getResultList();
        if(!result.isEmpty())
        {
            return result.get(0);
        }
        throw new CityNotFoundException("City " + name + " not found!");
    }

    public Forecast getForecast(String name){
        Forecast forecast = new Forecast();
        List<Forecast> result = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Forecast> criteria = builder.createQuery(Forecast.class);

        Root<Forecast> forecastRoot = criteria.from(Forecast.class);
        criteria.select(forecastRoot).where(builder.equal(forecastRoot.get("city").get("name"), name));
        TypedQuery<Forecast> query = entityManager.createQuery(criteria);
        result = query.getResultList();
        if(!result.isEmpty())
        {
            return result.get(0);
        }
        throw new ForecastNotFoundException("Forecast for city " + name + " not found!");
    }
}
