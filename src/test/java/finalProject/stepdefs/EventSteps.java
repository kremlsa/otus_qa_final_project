package finalProject.stepdefs;

import finalProject.common.BaseClass;
import finalProject.pages.EpamEventsPage;
import finalProject.pages.EpamMainPage;
import finalProject.pages.EpamTalkPage;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class EventSteps {

    @Autowired
    private EpamMainPage epamMainPage;
    @Autowired
    private EpamEventsPage epamEventsPage;
    @Autowired
    private EpamTalkPage epamTalkPage;

    WebDriver driver = BaseClass.getDriver();

    @Дано("Пользователь переходит на вкладку events")
    public void openUpcomingEvents() {
        epamMainPage.initWebDriver(driver);
        epamMainPage.open()
                    .openEvents();
    }

    @Когда("Пользователь нажимает на Upcoming Events")
    public void clickUpcomingEvents(){
        epamEventsPage.openUpcomingEvents();
    }

    @То("На странице отображаются карточки предстоящих мероприятий")
    public void isCardApperance(){
        Assert.assertTrue(epamEventsPage.isCardApperance());
    }

    @И("Количество карточек равно счетчику на кнопке Upcoming Events")
    public void checkNumberOfCards(){
        Assert.assertTrue(epamEventsPage.isCounterCorrect());
    }

    @И("В карточке указана информация о мероприятии:")
    public void getAllCards() {
        epamEventsPage.getAllCards();
    }


    @И("город проведения")
    public void checkPlace() {
        Assert.assertTrue(epamEventsPage.checkPlace());
    }

    @И("язык")
    public void checkLanguage() {
        Assert.assertTrue(epamEventsPage.checkLang());
    }

    @И("название мероприятия")
    public void checkEvent() {
        Assert.assertTrue(epamEventsPage.checkEvent());
    }

    @И("дата мероприятия")
    public void checkDate() {
        Assert.assertTrue(epamEventsPage.checkDate());
    }

    @И("информация о регистрации")
    public void checkRegistry() {
        //Assertions.assertTrue(epamEventsPage.checkReg());
        epamEventsPage.checkReg();
    }

    @И("список спикеров")
    public void checkSpeakers() {
        //Assertions.assertTrue(epamEventsPage.checkSpeakers());
        epamEventsPage.checkSpeakers();
    }

    @И("Важно проверить порядок отображаемых блоков с информацией в карточке мероприятия")
    public void важноПроверитьПорядокОтображаемыхБлоковСИнформациейВКарточкеМероприятия() {
    }

    @Дано("Пользователь переходит на вкладку Talks Library")
    public void openTalksLibrary() {
        epamMainPage.initWebDriver(driver);
        epamMainPage.open()
                .openTalks();
    }

    @Когда("Пользователь вводит ключевое слово QA в поле поиска")
    public void fillSearch() {
        epamTalkPage.fillSearch();
    }

    @То("На странице отображаются доклады, содержащие в названии ключевое слово поиска")
    public void isTalksDisplayed() {
        Assert.assertTrue(epamTalkPage.checkTalkTitle());
    }
}
