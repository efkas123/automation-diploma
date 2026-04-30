package api.apiclient;

import api.models.itemlist.AddItemRequestModel;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.base.TestBase.authorizedRequestSpec;
import static io.restassured.RestAssured.given;

public class ItemListApi {

    @Step("Добавление товара '{request.purchase}' в список покупок.")
    public static void addItem(AddItemRequestModel request) {
        var requestBuilder = given()
                .spec(authorizedRequestSpec)
                .contentType(ContentType.URLENC)
                .formParam("uuid", request.getUuid())
                .formParam("purchase", request.getPurchase());

        if (request.getSpecification() != null) {
            requestBuilder.formParam("specification", request.getSpecification());
        }

        requestBuilder
                .when()
                .put("/bringlists/" + request.getUuid())
                .then()
                .statusCode(204);

    }

    @Step("Получение списка покупок по uuid: '{listUuid}'.")
    public static Response getList(String listUuid) {
        return given()
                .spec(authorizedRequestSpec)
                .when()
                .get("/bringlists/" + listUuid)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Step("Удаление товара '{itemName}' из списка.")
    public static void removeItem(String listUuid, String itemName) {
        given()
                .spec(authorizedRequestSpec)
                .contentType(ContentType.URLENC)
                .formParam("uuid", listUuid)
                .formParam("recently", itemName)
                .when()
                .put("/bringlists/" + listUuid)
                .then()
                .statusCode(204);
    }

}
