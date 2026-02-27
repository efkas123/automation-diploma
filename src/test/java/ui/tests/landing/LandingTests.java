package ui.tests.landing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.base.TestBase;
import ui.pages.landing.LandingPage;

@DisplayName("Корректная загрузка элементов лендинг страницы Bring!.")
public class LandingTests extends TestBase {

    LandingPage landingPage = new LandingPage();

    @Test
    @DisplayName("Отображение заголовка 'The simplest shopping list for sharing.' на главной странице Bring!")
    void checkMainHeaderVisible() {
        landingPage
                .openPage()
                .assertSpecialHeaderVisibility();
    }

    @Test
    @DisplayName("Переадресация на страницу 'Why Bring?' при клике на соответствующую кнопку.")
    void successfulRedirectToWhyBringPage() {
        landingPage
                .openPage()
                .clickWhyBringButton()
                .assertRedirectToWhyBringPage();
    }


    @Test
    @DisplayName("Успешная смена языка с английского на немецкий.")
    void successfulSwitchToDeutsch() {
        landingPage
                .openPage()
                .clickLanguageDropDown()
                .clickDeutschButton();
    }

    @Test
    @DisplayName("Успешная смена языка с немецкого на английский")
    void successfulSwitchToEnglish() {
        landingPage
                .openPage()
                .clickLanguageDropDown()
                .clickDeutschButton()
                .clickLanguageDropDown()
                .clickEnglishButton()
                .assertSpecialHeaderVisibility();

                                                    /*
                                                        Здесь случился некоторый конфуз: у сайта нет ручки
                                                        bring.com/de/home, а при насильном открытии bring.com,
                                                        по геолокации всё равно сайт выбирает английский, поэтому
                                                        пришлось идти с костылями окольными путями.
                                                    */


    }

}
