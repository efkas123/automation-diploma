package api.tests.itemlist;

import api.base.TestBase;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ItemlistTests extends TestBase {

    @Test
    @DisplayName("Успешное получение списка продуктов от сервера.")
    @Tags({
            @Tag("TODO"),
            @Tag("TODO")
    })
    @Owner("Филипп Котов")
    void successfulGetItemlist200Test() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.getbring.com/rest/v2/bringlists/4430d23e-cc47-4bee-9a69-407331d8991e")
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("Успешное добавление товара в список покупок.")
    @Tags({
            @Tag("TODO"),
            @Tag("TODO")
    })
    @Owner("Филипп Котов")
    void succesffulItemAdditionToList200Test() {

        given()
                .contentType(ContentType.URLENC)
                .formParam("uuid", "4430d23e-cc47-4bee-9a69-407331d8991e")
                .formParam("purchase", "Trauben")
                .when()
                .put("https://api.getbring.com/rest/v2/bringlists/4430d23e-cc47-4bee-9a69-407331d8991e")
                .then()
                .log().all()
                .statusCode(204);

    }


}
