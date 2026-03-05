package api.base;

import api.models.itemlist.AddItemRequestModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.base.TestBase.authorizedRequestSpec;
import static io.restassured.RestAssured.given;

public class ItemListApi {

    public static void addItem(AddItemRequestModel request) {
        given()
                .spec(authorizedRequestSpec)
                .contentType(ContentType.URLENC)
                .formParam("uuid", request.getUuid())
                .formParam("purchase", request.getPurchase())
                .when()
                .put("/bringlists/" + request.getUuid())
                .then()
                .statusCode(204);

    }

    public static Response getList(String listUuid){
        return given()
                .spec(authorizedRequestSpec)
                .when()
                .get("/bringlists/" + listUuid)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public static void removeItem(String listUuid, String itemName){
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
