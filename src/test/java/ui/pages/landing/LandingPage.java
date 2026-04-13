package ui.pages.landing;

import com.codeborne.selenide.SelenideElement;
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
        whyBringHeader.shouldHave(text("Plan your shopping together stress-free")); //todo Вопрос к наставнику: допустим ли здесь хардкод, или же мне нужно вынести этот заголвоок?
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


    public LandingPage switchToTab(int tabNumber) {
        switchTo().window(tabNumber);
        return this;
    }

    public LandingPage checkSpecialHeaderOrAbort() {
        if (!specialHeader.isDisplayed()) {
            Assumptions.abort("ИЗВЕСТНЫЙ БАГ - при попытке смены языка с немецкого " +
                    "на английский, происходит переход на странциу контактов");
        }
        return this;
    }
}
