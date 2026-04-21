package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigProvider;
import config.UIConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

        if ("remote".equalsIgnoreCase(cfg.env())) {
            Configuration.remote = cfg.remoteUrl();
        } else {
            Configuration.remote = null;
        }


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        browserCapabilities = capabilities;

    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
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
