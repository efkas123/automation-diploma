package api.base;

import api.models.itemlist.AddItemRequestModel;
import io.restassured.response.Response;

import static api.specs.itemlist.AddItemToListSpec.addItemRequestSpec;
import static io.restassured.RestAssured.given;

public class ItemListApi {

    public static void addItem(AddItemRequestModel request) {
        given()
                .spec(addItemRequestSpec)
                .formParam("uuid", request.getUuid())
                .formParam("purchase", request.getPurchase())
                .when()
                .put("/bringlists/" + request.getUuid())
                .then()
                .statusCode(204);

    }

    public static Response getList(String listUuid){
        return given()
                .spec(addItemRequestSpec)
                .when()
                .get("/bringlists/" + listUuid)
                .then()
                .statusCode(200)
                .extract().response();
    }

}
