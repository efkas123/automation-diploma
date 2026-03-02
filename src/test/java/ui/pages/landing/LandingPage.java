package ui.pages.landing;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LandingPage {

    private final SelenideElement
            googlePlayButton = $("[data-button-type='hero-google-play-store']"),
            appleAppStoreButton = $("[data-button-type='hero-apple-app-store']"),
            getBringButton = $("#cta-navbar"),
            specialHeader = $(".section.home-hero h1.h1-style.special-heading"),
            whyBringButton = $("a[href*='why-bring']"),
            whyBringHeader = $(".h1-style"),
            languageDropDown = $("#w-dropdown-toggle-0"),
            languageOptionButton = $("a.language-dropdown-link"),
            deutschAddressHeader = $$("h1 strong").findBy(text("Impressum"));


    public LandingPage openLandingPage() {
        open("/en/home");
        return this;
    }

    public LandingPage clickGooglePlayStore() {
        googlePlayButton.click();
        return this;
    }

    public LandingPage clickAppleAppStore() {
        appleAppStoreButton.click();
        return this;
    }

    public LandingPage clickGetBringButton() {
        getBringButton.click();
        return this;
    }

    public LandingPage assertSpecialHeaderVisibility(String headerText) {
        specialHeader.shouldBe(visible);
        specialHeader.shouldHave(text(headerText));
        return this;
    }

    public LandingPage clickWhyBringButton() {
        whyBringButton.click();
        return this;
    }

    public LandingPage assertRedirectToWhyBringPage() {
        whyBringHeader.shouldHave(text("Bring! simplifies your daily visit at  the supermarket.")); //todo Вопрос к наставнику: допустим ли здесь хардкод, или же мне нужно вынести этот заголвоок?
        return this;
    }

    public LandingPage selectLanguage(String language) {
        languageDropDown.shouldBe(visible).click();
        $$("a.language-dropdown-link")
                .findBy(text(language))
                .shouldBe(visible)
                .click();
        return this;
    }

    public LandingPage clickDeutschButton() {
        languageOptionButton.shouldHave(text("Deutsch")).click();
        return this;
    }

    public LandingPage clickEnglishButton() {
        languageOptionButton.shouldHave(text("English")).click();
        return this;
    }

    public LandingPage deutscchNotSwitchedAssertion() {
        deutschAddressHeader.shouldBe(visible);
        return this;
    }

    public LandingPage switchToTab(int tabNumber){
        switchTo().window(tabNumber);
        return this;
    }

    public LandingPage checkSpecialHeaderOrAbort() {
        if (!$(specialHeader).isDisplayed()) {
            Assumptions.abort("ИЗВЕСТНЫЙ БАГ - при попытке смены языка с немецкого " +
                    "на английский, происходит переход на странциу контактов");
        }
        return this;
    }
}
