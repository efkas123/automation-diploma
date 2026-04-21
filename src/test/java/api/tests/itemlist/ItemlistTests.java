package api.tests.itemlist;

import api.base.ItemListApi;
import api.base.TestBase;
import api.models.itemlist.AddItemRequestModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@DisplayName("Корректная работа списка продуктов.")
@Epic("Список продуктов.")
@Feature("Главная страница приложения.")
@Story("Работа списка продуктов.")
@Tags({
        @Tag("API"),
        @Tag("Список покупок")
})
public class ItemlistTests extends TestBase {

    @Test
    @DisplayName("Успешное получение списка продуктов от сервера.")
    @Tags({
            @Tag("Позитивный")
    })
    @Owner("Филипп Котов")
    void successfulGetItemlist200Test() {
        ItemListApi.getList(bringListUuid);
    }

    @Test
    @DisplayName("Успешное добавление товара в список покупок.")
    @Tags({
            @Tag("Позитивный"),
            @Tag("Advanced")
    })
    @Owner("Филипп Котов")
    void succesffulItemAdditionToList200Test() {

        String purchase = "Trauben";

        AddItemRequestModel request = new AddItemRequestModel();

        request.setPurchase(purchase);
        request.setUuid(bringListUuid);

        ItemListApi.addItem(request);

        ItemListApi.getList(bringListUuid)
                .then()
                .body(containsString(purchase));

    }

    @Test
    @DisplayName("Успешное добавление товара с описанием в список покупок.")
    @Tags({
            @Tag("Позитивный"),
            @Tag("Advanced")
    })
    @Owner("Филипп Котов")
    void succesffulItemWithDescriptionAdditionToList200Test() {

        String purchase = "Trauben";
        String description = "Кишмиш";
        AddItemRequestModel request = new AddItemRequestModel();

        request.setPurchase(purchase);
        request.setUuid(bringListUuid);
        request.setSpecification(description);

        ItemListApi.addItem(request);

        ItemListApi.getList(bringListUuid)
                .then()
                .body(containsString(purchase))
                .body("purchase.find { it.name == '%s' }.specification".formatted(purchase), notNullValue());

    }


    @Test
    @DisplayName("Успешное удаление товара из списка покупок.")
    @Tags({
            @Tag("Позитивный"),
            @Tag("Advanced")
    })
    @Owner("Филипп Котов")
    void successfullyDeleteItemFromList200Test() {
        String purchase = "Trauben";
        String specification = "тропический";

        AddItemRequestModel request = new AddItemRequestModel();

        request.setPurchase(purchase);
        request.setSpecification(specification);
        request.setUuid(bringListUuid);

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
