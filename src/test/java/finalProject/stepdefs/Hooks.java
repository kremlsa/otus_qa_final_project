package finalProject.stepdefs;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import org.apache.logging.log4j.Logger;
import wtf.actions.Base;


import static wtf.cfg.Cfg.setBrowserConfiguration;


/**
 * Класс для запуска "Хуков" до и после тестов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Hooks {

    public Logger logger = Base.logger;

    @Before
    public void setUp() {
        setBrowserConfiguration();
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