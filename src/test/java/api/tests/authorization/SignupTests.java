package api.tests.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@DisplayName("Работа регистрации через API.")
@Epic("Регистрация")
@Feature("Bringauth API")
@Story("Регистрация пользователя.")
public class SignupTests {

    @Test
    @DisplayName("Попытка регистрации пользователя с обходом капчи")
    @Tags({
            @Tag("API"),
            @Tag("Негативный"),
            @Tag("Регистрация")
    })
    void signupAttemptTest(){
        given()
                .when()
                .post("https://api.getbring.com/rest/v2/bringauth/signup")
                .then()
                .statusCode(400)
                .log().all();
    }


}
