package utilities;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class LoadConfig {

    private final Config config;
    public static final LoadConfig CONFIG = getInstance();

    private LoadConfig() {
        this.config = ConfigFactory.load("app.conf");
    }

    public String getProperty(String key) {
        String environment = System.getProperty("env");
        Config config = this.config.getConfig(environment);
        return config.getString(key);
    }

    public String getProperty(AppProperty key) {
        return getProperty(key.getKey());
    }

    public static LoadConfig getInstance() {
        return new LoadConfig();
    }

}
