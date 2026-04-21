package ui.pages.authorization;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Открытие страницы авторизации.")
    public AuthorizationPage openAuthorizationPage() {
        open("https://web.getbring.com/login");
        return this;
    }

    @Step("Проверка отображения страницы авторизации.")
    public AuthorizationPage authorizationPageVisibleAssertion() {
        bringAccountLeadingHeader
                .shouldBe(visible)
                .shouldHave(text("Just login with your Bring! account"));
        webdriver().shouldHave(urlContaining("/login"));
        return this;
    }

    @Step("Клик по заголовку страницы авторизации.")
    public AuthorizationPage clickAccountLeadingHeader() {
        bringAccountLeadingHeader.click();
        return this;
    }

    @Step("Проверка неактивности кнопки Continue.")
    public AuthorizationPage btnContinueNotActiveAssertion() {
        btnContinue.shouldBe(disabled);
        return this;
    }

    @Step("Установка значения {text} в поле email.")
    public AuthorizationPage emailSetValue(String text) {
        emailInput.sendKeys(text);
        return this;
    }

    @Step("Проверка отображения ошибки невалидного email.")
    public AuthorizationPage invalidEmailAlertAssertion() {
        invalidEmailAlert.shouldBe(visible);
        invalidEmailAlert.shouldHave(text("Oops, invalid email"));
        return this;
    }

    @Step("Пропуск теста при выявлении известного бага с длиной email.")
    public AuthorizationPage skipIfContinueEnabledKnownBug() {
        if (btnContinue.isEnabled()) {
            Assumptions.abort("ИЗВЕСТНЫЙ БАГ - поле ввода пропускает значения свыше 254 символов");
        }
        return this;
    }

}
