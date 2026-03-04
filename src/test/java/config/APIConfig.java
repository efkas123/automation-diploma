package config;

import org.aeonbits.owner.Config;



@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/api.properties",
        "classpath:config/api.local.properties",
        "system:env",
        "system:properties"
})

public interface APIConfig extends Config {
    @Key("api.baseUri")
    @DefaultValue("https://api.getbring.com")
    String baseUri();

    @Key("api.basePath")
    @DefaultValue("/rest/v2")
    String basePath();

    @Key("api.authPath")
    @DefaultValue("/bringauth")
    String authPath();

    @Key("api.username")
    String username();

    @Key("api.password")
    String password();
}
