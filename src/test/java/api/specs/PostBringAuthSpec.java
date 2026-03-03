package api.specs;

import config.APIConfig;
import config.ConfigProvider;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class PostBringAuthSpec {

    static APIConfig api = ConfigProvider.api();

    public static RequestSpecification postBringAuthSpec = with()
            .filter(withCustomTemplates())
            .baseUri(api.baseUri())
            .basePath(api.basePath())
            .contentType(ContentType.URLENC)
            .log().uri()
            .log().method()
            .log().body()
            .log().headers();

}
