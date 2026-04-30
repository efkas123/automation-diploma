package ui.tests.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.authorization.AuthorizationPage;

import static utils.TestData.*;

@DisplayName("Корректная работа авторизации.")
@Epic("Авторизация")
@Feature("Страница /login")
@Story("Валидная работа элементов страницы авторизации.")
@Tags({
        @Tag("UI"),
        @Tag("Авторизация")
})
public class AuthorizationTests extends TestBase {

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Поле вв1ода email не принимает значения, не соответствующие маске value@domain.name")
    @Tags({
            @Tag("Негативный")
    })
    @Owner("Филипп Котов")
    void emailInputDoesntAcceptNonMaskTest() {

    authorizationPage
            .openAuthorizationPage()
            .emailSetValue(NON_EMAIL_MASK_VALUE)
            .btnContinueNotActiveAssertion();

    }

    @Test
    @DisplayName("Поле ввода email не должно принимать значения больше 254 символов")
    @Tags({
            @Tag("Негативный"),
            @Tag("Баг")
    })
    @Owner("Филипп Котов")
    void emailInputShouldntAcceptValuesHigherThan254Test() {

        authorizationPage
                .openAuthorizationPage()
                .emailSetValue(TOO_LONG_EMAIL)
                .skipIfContinueEnabledKnownBug()
                .btnContinueNotActiveAssertion();

    }

    @Test
    @DisplayName("Поле ввода email не должно принимать значения меньше 7 символов")
    @Tags({
            @Tag("Негативный")
    })
    @Owner("Филипп Котов")
    void emailInputShouldntAcceptValuesLowerThan7Test() {

        authorizationPage
                .openAuthorizationPage()
                .emailSetValue(TOO_SHORT_EMAIL_VALUE)
                .clickAccountLeadingHeader()
                .invalidEmailAlertAssertion()
                .btnContinueNotActiveAssertion();

    }

}
