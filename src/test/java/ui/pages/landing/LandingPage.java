package ui.pages.landing;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LandingPage {

    private final SelenideElement
            googlePlayButton = $("[data-button-type='hero-google-play-store']"),
            appleAppStoreButton = $("[data-button-type='hero-apple-app-store']"),
            getBringButton = $("#cta-navbar");



}
