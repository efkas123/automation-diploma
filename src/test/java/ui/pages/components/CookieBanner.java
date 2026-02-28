package ui.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CookieBanner {

    private final SelenideElement
            cookieAccept = $("#consent-accept");

    public void acceptCookieIfVisible(){
        if (cookieAccept.exists()){
            cookieAccept.click();
        }
    }
}
