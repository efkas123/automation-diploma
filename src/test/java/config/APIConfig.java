package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Sources({
        "system:properties",
        "system:env",
        "classpath:config/api.properties"
})

public interface APIConfig extends Config {
    @Key("api.baseUri")
    @DefaultValue("https://api.getbring.com")
    String baseUri();

    @Key("api.basePath")
    @DefaultValue("rest/v2")
    String basePath();

    @Key("api.authPath")
    @DefaultValue("/bringauth")
    String authPath();

    @Key("api.username")
    String username();

    @Key("api.password")
    String password();
}
