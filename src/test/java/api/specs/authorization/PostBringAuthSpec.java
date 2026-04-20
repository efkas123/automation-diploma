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
import static org.hamcrest.Matchers.*;

public class PostBringAuthSpec {

    public static RequestSpecification postBringAuthRequestSpec = with()
            .spec(baseRequestSpec)
            .contentType(ContentType.URLENC)
            .log().uri()
            .log().method()
            .log().headers();

    public static ResponseSpecification postBringAuthResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("$", hasKey("uuid"))
            .expectBody("$", hasKey("name"))
            .expectBody("email", not(emptyOrNullString()))
            .build();

    public static ResponseSpecification unsuccessfulBadCredentialLoginSpec = new ResponseSpecBuilder()
            .addResponseSpecification(baseResponseSpec)
            .expectStatusCode(401)
            .expectBody("message", equalTo("email password combination not existing"))
            .expectBody("error", equalTo("unauthorized"))
            .expectBody("error_description", equalTo("Not authorized to access this resource"))
            .log(ALL)
            .build();

}


