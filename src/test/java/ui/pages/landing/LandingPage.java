package ui.pages.landing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class LandingPage {

    private final SelenideElement
            googlePlayButton = $("[data-button-type='hero-google-play-store']"),
            appleAppStoreButton = $("[data-button-type='hero-apple-app-store']"),
            getBringButton = $("#cta-navbar"),
            specialHeader = $(".section.home-hero h1.h1-style.special-heading"),
            whyBringButton = $("a[href*='why-bring']"),
            whyBringHeader = $(".h1-style"),
            languageDropDown = $("#w-dropdown-toggle-0"),
            languageOptionButton = $("a.language-dropdown-link");


    public LandingPage openPage(){
        open("/en/home");
        return this;
    }

    public LandingPage openPageInDeutsch(){
        open("/");
        return this;
    }

    public LandingPage clickGooglePlayStore(){
        googlePlayButton.click();
        return this;
    }

    public LandingPage clickAppleAppStore(){
        appleAppStoreButton.click();
        return this;
    }

    public LandingPage clickBringWebApp() {
        getBringButton.click();
        return this;
    }

    public LandingPage assertSpecialHeaderVisibility(){
        specialHeader.shouldBe(visible);
        specialHeader.shouldHave(text("The simplest shopping list for sharing."));
        return this;
    }

    public LandingPage clickWhyBringButton(){
        whyBringButton.click();
        return this;
    }

    public LandingPage assertRedirectToWhyBringPage(){
        whyBringHeader.shouldHave(text("Bring! simplifies your daily visit at  the supermarket.")); //todo Вопрос к наставнику: допустим ли здесь хардкод, или же мне нужно вынести этот заголвоок?
        return this;
    }

    public LandingPage clickLanguageDropDown(){
        languageDropDown.shouldBe(visible);
        languageDropDown.click();
        return this;
    }

    public LandingPage clickDeutschButton(){
        languageOptionButton.shouldHave(text("Deutsch")).click();
        return this;
    }

    public LandingPage clickEnglishButton(){
        languageOptionButton.shouldHave(text("English")).click();
        return this;
    }
}
