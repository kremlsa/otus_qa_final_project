package finalProject.stepdefs;

import finalProject.pages.EpamTalkPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.testng.Assert;

/**
 * Класс для описания шагов Cucumber для сценариев
 * связанных со страницей сайта EPAM - "Video"
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class TalksStep {

    private EpamTalkPage epamTalkPage = new EpamTalkPage();

    @Когда("Пользователь вводит ключевое слово {string} в поле поиска")
    public void fillSearch(String query) {
        epamTalkPage.fillSearch(query);
    }

    @То("На странице отображаются доклады, содержащие в названии ключевое слово поиска")
    public void isTalksDisplayed() {
        Assert.assertTrue(epamTalkPage.checkTalkTitle());
    }

    @И("Пользователь нажимает на More Filters")
    public void clickMoreFilters() {
        epamTalkPage.clickMoreFilters();
    }

    @Когда("Пользователь выбирает: Category – {string}")
    public void selectCategoryTesting(String category) {
        epamTalkPage.filterCategory(category);
    }

    @И("Location – {string}")
    public void selectlocationBelarus(String location) {
        epamTalkPage.filterLocation(location);
    }

    @И("Language – {string} На вкладке фильтров")
    public void selectLanguageEnglish(String language) {
        epamTalkPage.filterLanguage(language);
    }

    @То("На странице отображаются карточки соответствующие правилам выбранных фильтров")
    public void isFilterWorks() {
        Assert.assertTrue(epamTalkPage.isFilterWorks());
    }
}
