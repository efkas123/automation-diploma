package api.base;

import api.apiclient.AuthApi;
import api.models.auth.AuthResponseModel;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static api.specs.BaseSpec.baseRequestSpec;

public class TestBase {


    protected static String token;
    protected static String bringListUuid;


    public static RequestSpecification authorizedRequestSpec;
    protected static AuthResponseModel authData;

    @BeforeAll
    static void beforeAll() {
        /*todo ОБРАЩАЮ ВНИМАНИЕ - в приложении удаление продукта реализовано через put,
        а в web версии удаление целого списка не реализовано, поэтому тесты на
        Delete в проекте не реализованы
         */
        authData = AuthApi.login();

        token = authData.getAccessToken();
        bringListUuid = authData.getBringListUUID();

        authorizedRequestSpec = new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec)
                .addHeader("Authorization", "Bearer " + token)
                .build();

    }
}
