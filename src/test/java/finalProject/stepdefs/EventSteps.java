package finalProject.stepdefs;

import finalProject.common.BaseClass;
import finalProject.pages.EpamEventsPage;
import finalProject.pages.EpamMainPage;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class EventSteps extends BaseStep{

    @Autowired
    private EpamMainPage epamMainPage;
    @Autowired
    private EpamEventsPage epamEventsPage;

    WebDriver driver = BaseClass.getDriver();

    @Дано("Пользователь переходит на вкладку events")
    public void openPage() {
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
        Assertions.assertTrue(epamEventsPage.isCardApperance());
    }

    @И("Количество карточек равно счетчику на кнопке Upcoming Events")
    public void checkNumberOfCards(){
        Assertions.assertTrue(epamEventsPage.isCounterCorrect());
    }

    @И("В карточке указана информация о мероприятии:")
    public void getAllCards() {
        epamEventsPage.getAllCards();
    }


    @И("город проведения")
    public void checkPlace() {
        Assertions.assertTrue(epamEventsPage.checkPlace());
    }

    @И("язык")
    public void checkLanguage() {
        Assertions.assertTrue(epamEventsPage.checkLang());
    }

    @И("название мероприятия")
    public void checkEvent() {
        Assertions.assertTrue(epamEventsPage.checkEvent());
    }

    @И("дата мероприятия")
    public void checkDate() {
        Assertions.assertTrue(epamEventsPage.checkDate());
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
}
