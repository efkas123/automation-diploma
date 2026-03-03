package api.tests.authorization;

import config.APIConfig;
import config.ConfigProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static api.specs.PostBringAuthSpec.*;
import static io.restassured.RestAssured.given;


public class SignupTests {

    APIConfig api = ConfigProvider.api();

    String userEmail = "sorry@automationdiploma.com";

    @Test
    @DisplayName("Неудачная попытка регистрации пользователя с обходом Captcha.")
    @Tags({
            @Tag("API"),
            @Tag("Негативный")
    })
    void signupAttemptTest() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + "null")
                .formParam("email", userEmail)
                .when()
                .post("https://api.getbring.com/rest/v2/bringauth")
                .then()
                .log().all();
    }

    @Test
    @DisplayName("Успешная попытка входа при вводе валидных учётных данных пользователя.")
    void loginAttemptTest() {
        given()
                .spec(postBringAuthSpec)        // application/x-www-form-urlencoded
                .formParam("email", api.username())
                .formParam("password", "L5AZhcdeTNSVd31m89jw8SNhz")
                .log().all()
                .when()
                .post("https://api.getbring.com/rest/v2/bringauth") // проверь эндпоинт в DevTools (у тебя в списке bringauth)
                .then()
                .log().all();
    }




}
