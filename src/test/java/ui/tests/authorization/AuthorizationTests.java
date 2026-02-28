package ui.tests.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.authorization.AuthorizationPage;


@Epic("Авторизация")
@Feature("Страница /login")
@Story("Валидная работа элементов страницы авторизации.")
public class AuthorizationTests extends TestBase {

    private final String
            tooLongEmailValue = "asdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffzv@gmail.com",
            tooShortEmailValue = "a@a.a";

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Поле ввода email не принимает значения, не соответствующие маске value@domain.name")
    @Tag("Позитивный")
    void emailInputDoesntAcceptNonMaskTest() {
        authorizationPage
                .openAuthorizationPage()                //todo fix Здесь возникла сложность: мой базовый url - www.getbring, а на странице авторизации - web.getbring. Как мне поступать в такой ситуации? Пока сделал костыль в виде хардкод ссылки в методе
                .btnContinueNotActiveAssertion();
    }

    @Test
    @DisplayName("Поле ввода email не должно принимать значения больше 254 символов")
    @Tags({
            @Tag("Негативный"),
            @Tag("Баг")
    })
    void emailInputShouldntAcceptValuesHigherThan254() {
        authorizationPage
                .openAuthorizationPage()
                .emailSetValue(tooLongEmailValue)
                .btnContinueNotActiveAssertion();
    }

    @Test
    @DisplayName("Поле ввода email не должно принимать значения меньше 7 символов")
    @Tags({
            @Tag("Негативный"),
            @Tag("Баг")
    })
    void emailInputShouldntAcceptValuesLowerThan7() {
        authorizationPage
                .openAuthorizationPage()
                .emailSetValue(tooShortEmailValue)
                .clickAccountLeadingHeader()
                .invalidEmailAlertAssertion()
                .btnContinueNotActiveAssertion();

    }

}
