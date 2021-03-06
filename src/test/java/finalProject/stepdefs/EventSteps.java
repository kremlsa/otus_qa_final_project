package finalProject.stepdefs;

import finalProject.pages.EpamEventsPage;
import finalProject.pages.EpamMainPage;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.qameta.allure.Step;
import org.testng.Assert;

/**
 * Класс для описания шагов Cucumber для сценариев
 * связанных со страницей сайта EPAM - "Events"
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class EventSteps {


    private EpamMainPage epamMainPage = new EpamMainPage();

    private EpamEventsPage epamEventsPage = new EpamEventsPage();

    @Дано("Пользователь переходит на вкладку events")
    public void openUpcomingEvents() {
        epamMainPage.open()
                    .openEvents();
    }

    @Когда("Пользователь нажимает на Upcoming Events")
    public void clickUpcomingEvents(){
        epamEventsPage.openUpcomingEvents();
    }

    @То("На странице отображаются карточки мероприятий")
    public void isCardApperance(){
        Assert.assertTrue(epamEventsPage.isCardApperance());
    }

    @И("Количество карточек равно счетчику на кнопке {string}")
    public void checkNumberOfCards(String buttonName){

        Assert.assertEquals(epamEventsPage.cardExist(), epamEventsPage.cardInCounter(buttonName),
                "Количество карточек не совпадает со значением в счётчике");
    }

    @И("В карточке указана информация о мероприятии:")
    public void getAllCards() {

    }

    @И("язык")
    public void checkLanguage() {
        epamEventsPage.checkLang();
    }

    @И("название мероприятия")
    public void checkEvent() {
        epamEventsPage.checkEvent();
    }

    @И("дата мероприятия")
    public void checkDate() {
        epamEventsPage.checkDate();
    }

    @И("информация о регистрации")
    public void checkRegistry() {
        epamEventsPage.checkReg();
    }

    @И("список спикеров")
    public void checkSpeakers() {
        epamEventsPage.checkSpeakers();
    }

    @И("все элементы присутствуют")
    public void isFieldFill() {
        Assert.assertTrue(epamEventsPage.isFieldFill());
    }


    @Step("Пользователь переходит на вкладку Talks Library")
    @Дано("Пользователь переходит на вкладку Talks Library")
    public void openTalksLibrary() {
        epamMainPage.open()
                .openTalks();
    }

    @Когда("Пользователь нажимает на Past Events")
    public void clickPastEvents() {
        epamEventsPage.openPastEvents();
    }

    @И("Пользователь нажимает на {string} в блоке фильтров и выбирает {string} в выпадающем списке")
    public void selectFilterValue(String filter, String value) {
        epamEventsPage.selectFilterValue(filter, value);
    }

    @И("Даты проведенных мероприятий меньше текущей даты")
    public void isDateLessCurrentDate() {
        Assert.assertTrue(epamEventsPage.isDateInCardLessCurrentDate());
    }

    @То("Даты проведения мероприятий больше или равны текущей дате или текущая дата находится в диапазоне дат проведения")
    public void checkUpcomingDate() {
        Assert.assertTrue(epamEventsPage.checkDateRange());
    }
}
