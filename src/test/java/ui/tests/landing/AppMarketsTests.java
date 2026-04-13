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
            @Tag("UI")
    })
    void successfulOpenPlayMarketBringPageTest(){

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openLandingPage();
        });

        step("Нажатие кнопки Google Play.", () -> {
            landingPage
                    .clickGooglePlayStore();
        });

        step("Переключение на соответствующую магазину вкладку.", () -> {
            landingPage.
                    switchToTab(1);
            webdriver().shouldHave(urlContaining("https://play.google.com/store/apps/"));
        });

        step("Проверка открытия страницы приложения в Google Play.", () -> {
            googleMarket
                    .assertAppName(appName);
        });
    }

    @Test
    @DisplayName("Успешное открытие страницы Bring! в Apple App Store")
    @Owner("Филипп Котов")
    @Tags({
            @Tag("App_Store"),
            @Tag("Позитивный"),
            @Tag("UI")
    })
    void successfulOpenAppStoreBringPageTest(){

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openLandingPage();
        });

        step("Нажатие кнопки App Store.", () -> {
            landingPage
                    .clickAppleAppStore();
        });

        step("Переключение на соответствующую магазину вкладку", () -> {
            switchTo().window(1);
            webdriver().shouldHave(urlContaining("https://apps.apple.com/"));
        });

        step("Проверка открытия страницы приложения в App Store", () -> {
            appStore
                    .assertAppName(appName);
        });

    }
}
