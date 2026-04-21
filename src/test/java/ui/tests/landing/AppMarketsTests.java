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
import ui.pages.appStores.AppleAppStorePage;
import ui.pages.appStores.GooglePlayMarketPage;
import ui.pages.landing.LandingPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static io.qameta.allure.Allure.step;

@DisplayName("Корректная переадресация на страницы магазинов приложений.")
@Epic("Лендинг")
@Feature("Магазины приложений")
@Story("Пользователь попадает в магазин приложений при клике на кнопку соответствующего магазина.")
public class AppMarketsTests extends TestBase {

    LandingPage landingPage = new LandingPage();
    GooglePlayMarketPage googleMarket = new GooglePlayMarketPage();
    AppleAppStorePage appStore = new AppleAppStorePage();

    private final String
            appName = "Bring!";

    @Test
    @DisplayName("Успешное открытие страницы Bring! в Google Play")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("Google_Play"),
            @Tag("Позитивный"),
            @Tag("UI"),
            @Tag("Магазины приложений")
    })
    void successfulOpenPlayMarketBringPageTest(){

        landingPage
                .openLandingPage()
                .clickGooglePlayStore()
                .switchToTab(1);

        step("Проверка переключения на соответствующую вкладку.", () -> {
            webdriver().shouldHave(urlContaining("https://play.google.com/store/apps/"));
        });

        googleMarket
                .assertAppName(appName);

    }

    @Test
    @DisplayName("Успешное открытие страницы Bring! в Apple App Store")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("App_Store"),
            @Tag("Позитивный"),
            @Tag("UI"),
            @Tag("Магазины приложений")
    })
    void successfulOpenAppStoreBringPageTest(){

        landingPage
                .openLandingPage()
                .clickAppleAppStore()
                .switchToTab(1);

        step("Проверка переключения на соответствующую вкладку.", () -> {
            webdriver().shouldHave(urlContaining("https://apps.apple.com/"));
        });

        appStore
                .assertAppName(appName);

    }
}
