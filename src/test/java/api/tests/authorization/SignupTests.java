package api.tests.authorization;

import config.APIConfig;
import config.ConfigProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static api.specs.BaseSpec.baseRequestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@DisplayName("Работа регистрации через API.")
@Epic("Регистрация")
@Feature("Bringauth API")
@Story("Регистрация пользователя.")
public class SignupTests {

    APIConfig api = ConfigProvider.api();

    @Test
    @DisplayName("Попытка регистрации пользователя без указания капча токена.")
    @Tags({
            @Tag("API"),
            @Tag("Негативный"),
            @Tag("Регистрация")
    })
    void signupWithoutCaptchTokenAttemptTest(){
        given()
                .spec(baseRequestSpec)
                .when()
                .post(api.authPath() + "/signup")
                .then()
                .statusCode(400)
                .contentType(containsString("text/html"))
                .body(containsString("HTTP Status 400"))
                .body(containsString("Bad Request"))
                .log().all();
    }


}
