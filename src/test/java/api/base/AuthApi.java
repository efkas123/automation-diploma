package api.base;

import api.models.auth.AuthResponseModel;
import config.APIConfig;
import config.ConfigProvider;

import static api.specs.authorization.PostBringAuthSpec.postBringAuthRequestSpec;
import static api.specs.authorization.PostBringAuthSpec.postBringAuthResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthApi {

    static APIConfig api = ConfigProvider.api();

    public static AuthResponseModel login() {
        AuthResponseModel response = given()
                .spec(postBringAuthRequestSpec)
                .formParam("email", api.username())
                .formParam("password", api.password())
                .when()
                .post(api.authPath())
                .then()
                .spec(postBringAuthResponseSpec)
                .extract().as(AuthResponseModel.class);
        return response;
    }

}
