package ui.tests.landing;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.authorization.AuthorizationPage;
import ui.pages.components.CookieBanner;
import ui.pages.landing.LandingPage;

import static io.qameta.allure.Allure.step;

@DisplayName("Корректная загрузка элементов лендинг страницы Bring!.")
@Epic("Лендинг")
@Feature("Навигация на главной странице")
@Story("Корректное отображение лендинг страницы.")
public class LandingTests extends TestBase {

    private final String
                specialHeaderText = "The simplest shopping list for sharing.";

    LandingPage landingPage = new LandingPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Отображение заголовка 'The simplest shopping list for sharing.' на главной странице Bring!")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный"),
            @Tag("UI")
    })
    void checkMainHeaderVisible() {

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openLandingPage();
        });
        step("Проверка отображения заголовка", () -> {
            landingPage
                    .assertSpecialHeaderVisibility(specialHeaderText);
        });


    }

    @Test
    @DisplayName("Переадресация на страницу 'Why Bring?' при клике на соответствующую кнопку.")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный"),
            @Tag("UI")
    })
    void successfulRedirectToWhyBringPage() {

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openLandingPage();
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
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный"),
            @Tag("UI")
    })
    void successfulSwitchToDeutsch() {

        step("Загрузка главной страницы", () -> {
            landingPage
                    .openLandingPage();
        });
        step("Выбор немецкого языка", () -> {
            landingPage
                    .selectLanguage("Deutsch");
        });



    }

    @Test
    @DisplayName("Успешная смена языка с немецкого на английский")
    @Tags({
            @Tag("Позитивный"),
            @Tag("Баг"),
            @Tag("UI"),
    })
    @Owner("Филипп Котов")
    void successfulSwitchToEnglish() {

        step("Открытие главной страницы", () -> {
            landingPage
                    .openLandingPage();
        });

        step("Accept cookies", () -> {
            new CookieBanner().acceptCookieIfVisible();
        });

        step("Смена языка на немецкий", () -> {
            landingPage
                    .selectLanguage("Deutsch");
        });
        step("Попытка смены языка на английский", () -> {
            landingPage
                    .selectLanguage("English");
        });
        step("Подтверждение успешной смены языка на английский", () -> {
            landingPage
                    .checkSpecialHeaderOrAbort()
                    .assertSpecialHeaderVisibility(specialHeaderText);
        });
 /*
     Здесь случился некоторый конфуз: у сайта нет ручки
     bring.com/de/home, а при насильном открытии bring.com,
     по геолокации всё равно сайт выбирает английский, поэтому
     пришлось идти с костылями окольными путями.
 */
    }

    @Test
    @DisplayName("Успешный переход с лендинга на страницу авторизации в Bring!.")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный"),
            @Tag("UI")
    })
    void successfulRedirectToBringAuthorizationPage(){
        step("Нажатие кнопки \"Get bring!\".", () -> {
            landingPage
                    .openLandingPage()
                    .clickGetBringButton();
        });

        step("Переключение на соответствующую вкладку.", () -> {
            landingPage.switchToTab(1);
        });

        step("Верификация результата.", () -> {
            authorizationPage
                    .authorizationPageVisibleAssertion();
        });
    }

}
