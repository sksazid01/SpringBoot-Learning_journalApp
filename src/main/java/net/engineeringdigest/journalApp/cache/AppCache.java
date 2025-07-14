package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.Config;
import net.engineeringdigest.journalApp.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public Map<String,String> APP_CACHE = new HashMap<>();

    @Autowired
    ConfigRepository configRepository;

    @PostConstruct  // call this method during startup
    public void init(){
        APP_CACHE = new HashMap<>();
        List<Config> all = configRepository.findAll();
        for(Config config:all){
            APP_CACHE.put(config.getKey(), config.getValue());
        }
    }
}
