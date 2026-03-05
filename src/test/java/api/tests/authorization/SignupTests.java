package api.tests.authorization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SignupTests {

    @Test
    @DisplayName("Попытка регистрации пользователя с обходом капчи")
    void signupAttemptTest(){
        given()
                .when()
                .post("https://api.getbring.com/rest/v2/bringauth/signup")
                .then()
                .log().all();
    }


}
