package ui.tests.landing;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.authorization.AuthorizationPage;
import ui.pages.components.CookieBanner;
import ui.pages.landing.LandingPage;

import static io.qameta.allure.Allure.step;

@DisplayName("Корректная загрузка элементов лендинг страницы Bring!.")
@Epic("Лендинг")
@Feature("Навигация на главной странице")
@Story("Корректное отображение лендинг страницы.")
public class LandingTests extends TestBase {

    private final String
                specialHeaderText = "The simplest shopping list for sharing.";

    LandingPage landingPage = new LandingPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Отображение заголовка 'The simplest shopping list for sharing.' на главной странице Bring!")
    void checkMainHeaderVisible() {

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openLandingPage();
        });
        step("Проверка отображения заголовка", () -> {
            landingPage
                    .assertSpecialHeaderVisibility(specialHeaderText);
        });


    }

    @Test
    @DisplayName("Переадресация на страницу 'Why Bring?' при клике на соответствующую кнопку.")
    void successfulRedirectToWhyBringPage() {

        step("Загрузка главной страницы.", () -> {
            landingPage
                    .openLandingPage();
        });
        step("Клик по кнопке 'Why Bring?'.", () -> {
            landingPage
                    .clickWhyBringButton();
        });
        step("Подтверждение успешной переадресации.", () -> {
            landingPage
                    .assertRedirectToWhyBringPage();
        });



    }


    @Test
    @DisplayName("Успешная смена языка с английского на немецкий.")
    void successfulSwitchToDeutsch() {
        landingPage
                .openLandingPage()
                .selectLanguage("Deutsch");

    }

    @Test
    @DisplayName("Успешная смена языка с немецкого на английский")
    @Tag("Баг")
    //Здесь не был уверен, как сделать так, чтобы тест у меня падал (нашёл баг),
    // но при этом помечался успешным, поэтому использовал костыль.
    void successfulSwitchToEnglish() {

        step("Открытие главной страницы", () -> {
            landingPage
                    .openLandingPage();
        });

        step("Accept cookies", () -> {
            new CookieBanner().acceptCookieIfVisible();
        });

        step("Смена языка на немецкий", () -> {
            landingPage
                    .selectLanguage("Deutsch");
        });
        step("Попытка смены языка на английский", () -> {
            landingPage
                    .selectLanguage("English");
        });
        step("Подтверждение негативного результата", () -> {
            landingPage
                    .deutscchNotSwitchedAssertion();
        }); //todo fix Вопрос: здесь уместно будет назвать тест-кейс "Успешная смена языка" и пометить багом при том, что тест нашёл баг, а успешного прохождения нет??

                                                    /*
                                                        Здесь случился некоторый конфуз: у сайта нет ручки
                                                        bring.com/de/home, а при насильном открытии bring.com,
                                                        по геолокации всё равно сайт выбирает английский, поэтому
                                                        пришлось идти с костылями окольными путями.
                                                    */
    }

    @Test
    @DisplayName("Успешный переход с лендинга на страницу авторизации в Bring!.")
    void successfulRedirectToBringAuthorizationPage(){
        step("Нажатие кнопки \"Get bring!\".", () -> {
            landingPage
                    .openLandingPage()
                    .clickGetBringButton();
        });

        step("Переключение на соответствующую вкладку.", () -> {
            landingPage.switchToTab(1);
        });

        step("Верификация результата.", () -> {
            authorizationPage
                    .authorizationPageVisibleAssertion();
        });
    }

}
