package ui.tests.landing;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.landing.LandingPage;

import static io.qameta.allure.Allure.step;

@DisplayName("Корректная загрузка элементов лендинг страницы Bring!.")
@Epic("Лендинг")
@Feature("Навигация на главной странице")
@Story("Корректное отображение лендинг страницы.")
public class LandingTests extends TestBase {

    LandingPage landingPage = new LandingPage();

    @Test
    @DisplayName("Отображение заголовка 'The simplest shopping list for sharing.' на главной странице Bring!")
    void checkMainHeaderVisible() {

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openPage();
        });
        step("Проверка отображения заголовка", () -> {
            landingPage
                    .assertSpecialHeaderVisibility();
        });


    }

    @Test
    @DisplayName("Переадресация на страницу 'Why Bring?' при клике на соответствующую кнопку.")
    void successfulRedirectToWhyBringPage() {

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openPage();
        });
        step("Клик по кнопке 'Why Bring?'.", () -> {
            landingPage
                    .clickWhyBringButton();
        });
        step("Подтверждение успешной переадресации.", () -> {
            landingPage
                    .assertRedirectToWhyBringPage();
        });



    }


    @Test
    @DisplayName("Успешная смена языка с английского на немецкий.")
    void successfulSwitchToDeutsch() {
        landingPage
                .openPage()
                .clickLanguageDropDown()
                .clickDeutschButton();
    }

    @Test
    @DisplayName("Успешная смена языка с немецкого на английский")
    @Disabled //Нашёл баг в функциональности.
    void successfulSwitchToEnglish() {
        landingPage
                .openPage()
                .clickLanguageDropDown()
                .clickDeutschButton()
                .clickLanguageDropDown()
                .clickEnglishButton()
                .assertSpecialHeaderVisibility();

                                                    /*
                                                        Здесь случился некоторый конфуз: у сайта нет ручки
                                                        bring.com/de/home, а при насильном открытии bring.com,
                                                        по геолокации всё равно сайт выбирает английский, поэтому
                                                        пришлось идти с костылями окольными путями.
                                                    */


    }

}
