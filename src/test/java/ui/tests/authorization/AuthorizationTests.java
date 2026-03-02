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

import static io.qameta.allure.Allure.step;


@Epic("Авторизация")
@Feature("Страница /login")
@Story("Валидная работа элементов страницы авторизации.")
public class AuthorizationTests extends TestBase {

    private final String
            tooLongEmailValue = "asdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffasdasdfffzv@gmail.com",
            tooShortEmailValue = "a@a.a",
            nonEmailMaskValue = ".@somevalue";

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Поле ввода email не принимает значения, не соответствующие маске value@domain.name")
    @Tags({
            @Tag("Негативный"),
            @Tag("UI")
    })
    @Owner("Филипп Котов")
    void emailInputDoesntAcceptNonMaskTest() {

        step("Загрузка главной страницы", () -> {
            authorizationPage.
                    openAuthorizationPage();
        });

        step("Ввод значения email, не соответствующего маске mail@example.com", () -> {
            authorizationPage
                    .emailSetValue(nonEmailMaskValue);
        });

        step("Проверка неактивности кнопки \"Continue\"", () -> {
            authorizationPage
                    .btnContinueNotActiveAssertion();
        });
        //todo fix Здесь возникла сложность: мой базовый url - www.getbring, а на
        // странице авторизации - web.getbring. Как мне поступать в такой ситуации?
        // Пока сделал костыль в виде хардкод ссылки в методе.

    }

    @Test
    @DisplayName("Поле ввода email не должно принимать значения больше 254 символов")
    @Tags({
            @Tag("Негативный"),
            @Tag("Баг"),
            @Tag("UI")
    })
    @Owner("Филипп Котов")
    void emailInputShouldntAcceptValuesHigherThan254() {

        step("Загрузка главной страницы", () -> {
            authorizationPage
                    .openAuthorizationPage();
        });

        step("Задание слишком длинного значения email (255 знаков)", () -> {
            authorizationPage
                    .emailSetValue(tooLongEmailValue);
        });


        step("Верификация результата.", () -> {
            authorizationPage
                    .skipIfContinueEnabledKnownBug()
                    .btnContinueNotActiveAssertion();
        });


    }

    @Test
    @DisplayName("Поле ввода email не должно принимать значения меньше 7 символов")
    @Tags({
            @Tag("Негативный"),
            @Tag("UI")
    })
    @Owner("Филипп Котов")
    void emailInputShouldntAcceptValuesLowerThan7() {

        step("Загрузка главной страницы.", () -> {
            authorizationPage
                    .openAuthorizationPage();
        });

        step("Задание слишком короткого значения email (6 знаков).", () -> {
            authorizationPage
                    .emailSetValue(tooShortEmailValue);
        });

        step("Клик не некликабельный элемент.", () -> {
            authorizationPage
                    .clickAccountLeadingHeader();
        });

        step("Верификация результата.", () -> {
            authorizationPage
                    .invalidEmailAlertAssertion()
                    .btnContinueNotActiveAssertion();
        });


    }

}
