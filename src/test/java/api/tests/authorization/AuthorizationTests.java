package api.tests.authorization;

import config.APIConfig;
import config.ConfigProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import utils.TestData;

import static api.specs.authorization.PostBringAuthSpec.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class AuthorizationTests {

    TestData random = new TestData();
    APIConfig api = ConfigProvider.api();

    @Test
    @DisplayName("Неудачная попытка регистрации пользователя с обходом Captcha.")
    @Tags({
            @Tag("API"),
            @Tag("Негативный")
    })
    void unsuccessfulSignupAttemptTest() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + "null")
                .formParam("email", random.userEmail)
                .when()
                .post("https://api.getbring.com/rest/v2/bringauth")
                .then()
                .statusCode(401)
                .log().all();
    }

    @Test
    @DisplayName("Успешная попытка входа при вводе валидных учётных данных пользователя.")
    @Tags({
            @Tag("API"),
            @Tag("Позитивный")
    })
    void SuccessfulLoginAttemptTest() {
        given()
                .spec(postBringAuthRequestSpec)
                .formParam("email", api.username())
                .formParam("password", api.password())
                .when()
                .post(api.authPath())
                .then()
                .spec(postBringAuthResponseSpec);
    }

    @Test
    @DisplayName("Успешное получение токена авторизации.")
    @Tags({
            @Tag("API"),
            @Tag("Позитивный")
    })
    void successfulAcquiringAccessTokenTest() {
        given()
                .spec(postBringAuthRequestSpec)
                .formParam("email", api.username())
                .formParam("password", api.password())
                .when()
                .post(api.authPath())
                .then()
                .spec(postBringAuthResponseSpec)
                .body("token_type", equalTo("Bearer"))
                .body("access_token", notNullValue());
    }

    @Test
    @DisplayName("Отказ в авторизации при передаче невалидных учётных данных пользователя.")
    void unsuccessfulBadCredentialsLoginTest(){
        given()
                .spec(postBringAuthRequestSpec)
                .formParam("email", random.userEmail)
                .formParam("password", random.userPassword)
                .when()
                .post(api.authPath())
                .then()
                .spec(unsuccessfulBadCredentialLoginSpec);
    }


}
