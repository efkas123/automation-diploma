package api.specs.authorization;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.specs.BaseSpec.baseRequestSpec;
import static api.specs.BaseSpec.baseResponseSpec;
import static helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class PostBringAuthSpec {

    public static RequestSpecification postBringAuthRequestSpec = with()
            .spec(baseRequestSpec)
            .filter(withCustomTemplates())
            .contentType(ContentType.URLENC)
            .log().uri()
            .log().method()
            .log().headers();

    public static ResponseSpecification postBringAuthResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification unsuccessfulBadCredentialLoginSpec = new ResponseSpecBuilder()
            .addResponseSpecification(baseResponseSpec)
            .expectStatusCode(401)
            .build();

}


