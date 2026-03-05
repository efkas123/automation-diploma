package api.tests.itemlist;

import api.base.ItemListApi;
import api.base.TestBase;
import api.models.itemlist.AddItemRequestModel;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ItemlistTests extends TestBase {

    @Test
    @DisplayName("Успешное получение списка продуктов от сервера.")
    @Tags({
            @Tag("Позитивный"),
            @Tag("UI")
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
            @Tag("Позитивный"),
            @Tag("UI"),
            @Tag("Advanced")
    })
    @Owner("Филипп Котов")
    void succesffulItemAdditionToList200Test() {

        String purchase = "Trauben";
        AddItemRequestModel request = new AddItemRequestModel(bringListUuid, purchase);

        ItemListApi.addItem(request);

        ItemListApi.getList(bringListUuid)
                .then()
                .body(containsString(purchase));

    }


    @Test
    @DisplayName("Успешное удаление товара из списка покупок.")
    @Tags({
            @Tag("Позитивный"),
            @Tag("UI"),
            @Tag("Advanced")
    })
    @Owner("Филипп Котов")
    void successfullyDeleteItemFromList200Test(){
        String purchase = "Trauben";
        AddItemRequestModel request = new AddItemRequestModel(bringListUuid, purchase);


        step("Добавление предмета в список покупок.", () -> {
            ItemListApi.addItem(request);
        });

        step("Удаление предмета из списка покупок.", () -> {
            ItemListApi.removeItem(bringListUuid, purchase);
        });

        step("Проверка результата.", () -> {
            ItemListApi.getList(bringListUuid)
                    .then()
                    .body("purchase.name", not(hasItem(purchase)))
                    .body("recently.name", hasItem(purchase));
        });




    }


}
