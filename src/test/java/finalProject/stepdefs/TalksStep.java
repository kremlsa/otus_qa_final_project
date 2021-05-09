package finalProject.stepdefs;

import finalProject.pages.EpamTalkPage;
import finalProject.pages.TalkCard;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
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

    @То("На странице отображаются карточки соответствующие правилам выбранных фильтров")
    public void isFilterWorks() {
        Assert.assertTrue(epamTalkPage.isFilterWorks());
    }
}
