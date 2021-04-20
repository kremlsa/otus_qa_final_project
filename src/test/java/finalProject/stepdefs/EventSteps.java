package finalProject.stepdefs;

import finalProject.common.BaseClass;
import finalProject.common.UniLoc;
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

    @То("На странице отображаются карточки мероприятий")
    public void isCardApperance(){
        Assert.assertTrue(epamEventsPage.isCardApperance());
    }

    @И("Количество карточек равно счетчику на кнопке {string}")
    public void checkNumberOfCards(String buttonName){
        Assert.assertTrue(epamEventsPage.isCounterCorrect(buttonName));
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
        //epamMainPage.initWebDriver(driver);
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
        epamEventsPage.getAllCards();
        Assert.assertTrue(epamEventsPage.isDateInCardLessCurrentDate());
    }

    @Когда("Пользователь нажимает на любую карточку")
    public void openAnyCard() {
        epamEventsPage.openAnyCard();
    }

    @То("Происходит переход на страницу с подробной информацией о мероприятии")
    public void isCardOpen() {
        
    }

    @И("На странице с информацией о мероприятии отображается шапка с кнопкой для регистрации")
    public void isHeaderPresent() {
        
    }

    @И("программа мероприятия")
    public void программаМероприятия() {
        
    }

    @И("карта")
    public void карта() {
    }

    @То("В блоке This week даты проведения мероприятий больше или равны текущей дате и находятся в пределах текущей недели")
    public void isDateInWeekAfter() {

    }
}
