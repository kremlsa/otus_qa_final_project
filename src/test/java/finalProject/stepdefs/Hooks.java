package finalProject.stepdefs;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import org.apache.logging.log4j.Logger;
import wtf.actions.Base;
import wtf.cfg.Cfg;

/**
 * Класс для запуска "Хуков" до и после тестов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Hooks {

    public Logger logger = Base.logger;
    Cfg cfg = new Cfg();

    @Before
    public void setUp() {
        cfg.setBrowserConfiguration();
        logger.info("Открыт браузер");
    }

    @AfterStep(value="@Selenide")
    private void setDownTest() {
        Selenide.clearBrowserLocalStorage();
        logger.info("Чистим браузер");
        Selenide.closeWebDriver();
        logger.info("Закрыт браузер");
    }

    @After
    public void closeSelenide() {
        Selenide.closeWebDriver();
        logger.info("Закрыт браузер");
    }

}