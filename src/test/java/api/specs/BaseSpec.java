package api.specs;

import config.APIConfig;
import config.ConfigProvider;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class BaseSpec {

    static APIConfig api = ConfigProvider.api();

    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri(api.baseUri())
            .basePath(api.basePath())
            .log().uri()
            .log().method();

    public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();

}
