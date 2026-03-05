package api.base;

import api.models.auth.AuthResponseModel;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static api.specs.BaseSpec.baseRequestSpec;

public class TestBase {


    protected static String token;
    protected static String bringListUuid;


    protected static RequestSpecification authorizedRequestSpec;
    protected static AuthResponseModel authData;

    @BeforeAll
    static void beforeAll() {
        //Здесь пришлось спросить у гпт, как по-умолчанию ко всем запросам добавить авторизацию.

        authData = AuthApi.login();

        token = authData.getAccessToken();
        bringListUuid = authData.getBringListUUID();

        authorizedRequestSpec = new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec)
                .addHeader("Authorization", "Bearer " + token)
                .build();

    }
}
