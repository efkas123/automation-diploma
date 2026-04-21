package ui.pages.appStores;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GooglePlayMarketPage {

    private final SelenideElement
            appHeader = $("[itemprop='name']");


    @Step("Верификация отображения '{value}' в Google Play Market.")
    public GooglePlayMarketPage assertAppName(String value) {
        appHeader.shouldBe(visible);
        appHeader.shouldHave(text(value));
        return this;
    }
}
