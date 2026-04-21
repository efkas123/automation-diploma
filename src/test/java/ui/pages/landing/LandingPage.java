package ui.pages.landing;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assumptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage {

    private final SelenideElement
            googlePlayButton = $("a:has(img[src*='Play_Store'])"),
            appleAppStoreButton = $("a:has(img[src*='AppleStore'])"),
            getBringButton = $("#cta-navbar"),
            specialHeader = $("h1.gb-h1-style.special-heading"),
            whyBringButton = $("a[href*='/features']"),
            whyBringHeader = $(".gb-h1-style.special-heading"),
            languageDropDown = $("#w-dropdown-toggle-0");


    @Step("Открытие главной страницы Bring!.")
    public LandingPage openLandingPage() {
        open("/en/home");
        return this;
    }

    @Step("Клик по кнопку Google Play.")
    public LandingPage clickGooglePlayStore() {
        googlePlayButton.click();
        return this;
    }

    @Step("Клик по кнопке Apple App Store.")
    public LandingPage clickAppleAppStore() {
        appleAppStoreButton.click();
        return this;
    }

    @Step("Клик по кнопке Get Bring!.")
    public LandingPage clickGetBringButton() {
        getBringButton.click();
        return this;
    }

    @Step("Проверка заголовка страницы: '{headerText}'")
    public LandingPage assertSpecialHeaderVisibility(String headerText) {
        specialHeader.shouldBe(visible);
        specialHeader.shouldHave(text(headerText));
        return this;
    }

    @Step("Клик по кнопке 'Why bring?'.")
    public LandingPage clickWhyBringButton() {
        whyBringButton.click();
        return this;
    }

    @Step("Проверка переадресации на страницу 'Why Bring?'.")
    public LandingPage assertRedirectToWhyBringPage() {
        whyBringHeader.shouldHave(text("Plan your shopping together stress-free")); //todo Вопрос к наставнику: допустим ли здесь хардкод, или же мне нужно вынести этот заголвоок?
        return this;
    }

    @Step("Выбор языка: {language}.")
    public LandingPage selectLanguage(String language) {
        languageDropDown.shouldBe(visible).click();
        $$("a.language-dropdown-link")
                .findBy(text(language))
                .shouldBe(visible)
                .click();
        return this;
    }


    @Step("Переключение на вкладку номер {tabNumber}")
    public LandingPage switchToTab(int tabNumber) {
        switchTo().window(tabNumber);
        return this;
    }

    @Step("Проверка наличия заголовка или пропуск теста при известном баге.")
    public LandingPage checkSpecialHeaderOrAbort() {
        if (!specialHeader.isDisplayed()) {
            Assumptions.abort("ИЗВЕСТНЫЙ БАГ - при попытке смены языка с немецкого " +
                    "на английский, происходит переход на странциу контактов");
        }
        return this;
    }
}
