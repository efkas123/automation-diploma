package ui.pages.appStores;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AppleAppStorePage {

    private final SelenideElement
        appHeader = $("h1.svelte-kps97o");

    public AppleAppStorePage assertAppName(String value){
        appHeader.shouldBe(visible);
        appHeader.shouldHave(text(value));
        return this;
    }
}
