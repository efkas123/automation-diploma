package ui.pages.appStores;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GooglePlayMarketPage {

    private final SelenideElement
            appHeader = $("[itemprop='name']");



    public GooglePlayMarketPage assertAppName(String value) {
        appHeader.shouldBe(visible);
        appHeader.shouldHave(text(value));
        return this;
    }
}
