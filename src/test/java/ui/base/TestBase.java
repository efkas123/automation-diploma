package ui.base;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setuUp() {
        browserSize = "1920x1080";
        browser= "chrome";
        browserVersion = "128";
        remote = System.getProperty("remoteUrl");
        baseUrl = "https://www.getbring.com";
        pageLoadStrategy = "eager";
    }

    @BeforeEach
    void beforeEachSetUp(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        Attachments.screenshotAs("Last Screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        closeWebDriver();
    }
}
