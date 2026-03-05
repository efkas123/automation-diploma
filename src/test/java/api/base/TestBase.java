package api.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static api.specs.BaseSpec.baseRequestSpec;

public class TestBase {


    protected static String token;
    protected static RequestSpecification authorizedRequestSpec;

    @BeforeAll
    static void beforeAll() {
        //Здесь пришлось спросить у гпт, как по-умолчанию ко всем запросам добавить авторизацию.



        token = AuthApi.getAccessToken();

        authorizedRequestSpec = new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        RestAssured.requestSpecification = authorizedRequestSpec;



    }
}
