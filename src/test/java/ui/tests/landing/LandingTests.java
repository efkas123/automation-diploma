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
@Tags({
        @Tag("UI"),
        @Tag("Лендинг")
})
public class LandingTests extends TestBase {

    private final String
                specialHeaderText = "The easiest shopping list to share",
                deutschSpecialHeaderText = "Die kostenlose Einkaufslisten- App zum Teilen";

    LandingPage landingPage = new LandingPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Отображение заголовка 'The simplest shopping list for sharing.' на главной странице Bring!")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный")
    })
    void checkMainHeaderVisibility() {

        landingPage
                .openLandingPage()
                .assertSpecialHeaderVisibility(specialHeaderText);

    }

    @Test
    @DisplayName("Переадресация на страницу 'Why Bring?' при клике на соответствующую кнопку.")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный")
    })
    void successfulRedirectToWhyBringPage() {

        landingPage
                .openLandingPage()
                .clickWhyBringButton()
                .assertRedirectToWhyBringPage();

    }


    @Test
    @DisplayName("Успешная смена языка с английского на немецкий.")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный")
    })
    void successfulSwitchToDeutschTest() {

        landingPage
                .openLandingPage()
                .selectLanguage("Deutsch")
                .assertSpecialHeaderVisibility(deutschSpecialHeaderText);

    }

    @Test
    @DisplayName("Успешная смена языка с немецкого на английский")
    @Tags({
            @Tag("Позитивный"),
            @Tag("Баг")
    })
    @Owner("Филипп Котов")
    void successfulSwitchToEnglishTest() {

        landingPage
                .openLandingPage();

        step("Accept cookies", () -> {
            new CookieBanner().acceptCookieIfVisible();
        });

        landingPage
                .selectLanguage("Deutsch")
                .selectLanguage("English")
                .checkSpecialHeaderOrAbort()
                .assertSpecialHeaderVisibility(specialHeaderText);


    }

    @Test
    @DisplayName("Успешный переход с лендинга на страницу авторизации в Bring!.")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Позитивный")
    })
    void successfulRedirectToBringAuthorizationPageTest(){

        landingPage
                .openLandingPage()
                .clickGetBringButton()
                .switchToTab(1);

        authorizationPage
                .authorizationPageVisibleAssertion();
    }

}
