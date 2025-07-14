package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api_key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();


//        // You can send post request
//        User user = (User) User.builder().username("sk").password("sk").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
//        return response.getBody();


    }
}
