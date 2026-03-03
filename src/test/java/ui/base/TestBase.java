package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigProvider;
import config.UIConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUp() {
        UIConfig cfg = ConfigProvider.ui();


        browser = cfg.browser();
        browserVersion = cfg.browserVersion();
        browserSize = cfg.browserSize();
        baseUrl = cfg.baseUrl();
        pageLoadStrategy = "eager";

        String remote = cfg.remoteUrl();
        if (remote != null && !remote.isBlank()) {
            Configuration.remote = remote;
        } else {
            Configuration.remote = null; // важно: null, не ""
        }


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        //todo fix вопрос: мне стоит добавлять listener в before all или before each?
        //Как понимаю, всё же в before all.
    }


    @AfterEach
    void tearDown() {
        Attachments.screenshotAs("Last Screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }
}
