package app.components.controller;

import app.components.service.ForecastService;
import app.components.view.ForecastCityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "find", produces = APPLICATION_JSON_VALUE)
public class FromBaseController {

    private ForecastService service;

    @Autowired
    public void setService(ForecastService service) {
        this.service = service;
    }

    @RequestMapping(value = "/view_from_base", method = {GET})
    public ForecastCityView getFromBase(@RequestParam(value="city", required=true) String city){
        return service.getForecast(city);
    }
}
