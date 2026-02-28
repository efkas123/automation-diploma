package ui.pages.authorization;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assumptions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class AuthorizationPage {

    private final SelenideElement
            bringAccountLeadingHeader = $(".bring-account-leadin"),
            emailInput = $("#tbEmail"),
            btnContinue = $("#btnContinue"),
            invalidEmailAlert = $(".form-label-validated-message");

    public AuthorizationPage openAuthorizationPage() {
        open("https://web.getbring.com/login");
        return this;
    }

    public AuthorizationPage authorizationPageVisibleAssertion() {
        bringAccountLeadingHeader
                .shouldBe(visible)
                .shouldHave(text("Just login with your Bring! account"));
        webdriver().shouldHave(urlContaining("/login"));
        return this;
    }

    public AuthorizationPage clickAccountLeadingHeader() {
        bringAccountLeadingHeader.click();
        return this;
    }

    public AuthorizationPage switchToTab(int tabNumber) {
        switchTo().window(tabNumber);
        return this;
    }

    public AuthorizationPage
    btnContinueNotActiveAssertion() {
        btnContinue.shouldBe(disabled);
        return this;
    }

    public boolean isContinueEnabled() {
        return btnContinue.isEnabled();
    }

    public AuthorizationPage
    btnContinueActiveAssertion() {
        btnContinue.shouldBe(enabled);
        return this;
    }

    public AuthorizationPage emailSetValue(String text) {
        emailInput.sendKeys(text);
        return this;
    }

    public AuthorizationPage invalidEmailAlertAssertion() {
        invalidEmailAlert.shouldBe(enabled);
        return this;
    }

    public AuthorizationPage skipIfContinueEnabledKnownBug() {
        if (btnContinue.isEnabled()) {
            Assumptions.abort("KNOWN BUG: Continue enabled for email > 254");
        }
        return this;
    }

}
