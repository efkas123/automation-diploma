package ui.tests.landing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.appStores.AppleAppStorePage;
import ui.pages.appStores.GooglePlayMarketPage;
import ui.pages.landing.LandingPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@DisplayName("Открытие страниц приложения Bring в магазинах приложений")
public class AppMarketsTests extends TestBase {

    LandingPage landingPage = new LandingPage();
    GooglePlayMarketPage googleMarket = new GooglePlayMarketPage();
    AppleAppStorePage appStore = new AppleAppStorePage();

    private final String
            appName = "Bring!";

    @Test
    @DisplayName("Успешное открытие страницы Bring! в Google Play Market")
    void successfulOpenPlayMarketBringPage(){
        landingPage
                .openPage()
                .clickGooglePlayStore();

        switchTo().window(1); //Переключение на таб с playMarket
        webdriver().shouldHave(urlContaining("https://play.google.com/store/apps/"));


        googleMarket
                .assertAppName(appName);

    }

    @Test
    @DisplayName("Успешное открытие страницы Bring! в Apple App Store")
    void successfulOpenAppStoreBringPage(){
        landingPage
                .openPage()
                .clickAppleAppStore();

        switchTo().window(1);
        webdriver().shouldHave(urlContaining("https://apps.apple.com/"));

        appStore
                .assertAppName(appName);
    }
}
