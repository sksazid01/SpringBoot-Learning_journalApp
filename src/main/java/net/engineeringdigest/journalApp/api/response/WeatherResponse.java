package net.engineeringdigest.journalApp.api.response;

import lombok.Data;

import java.util.List;

// Json to POJO converter

@Data
public class WeatherResponse {
    private WeatherResponse.Request request;
    private WeatherResponse.Location location;
    private WeatherResponse.Current current;


    @Data
    public class Current {
        private String observation_time;
        private int temperature;
        private int weather_code;
        private List<String> weather_icons;
        private List<String> weather_descriptions;

        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
        private String is_day;
    }

    @Data
    public class Location {
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        private String timezone_id;
        private String localtime;
        private int localtime_epoch;
        private String utc_offset;
    }

    @Data
    public class Request {
        private String type;
        private String query;
        private String language;
        private String unit;
    }

}
