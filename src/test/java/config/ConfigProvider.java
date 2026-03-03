package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private ConfigProvider() {}

    public static UIConfig ui() {
        return ConfigFactory.create(UIConfig.class, System.getProperties());
    }
}
