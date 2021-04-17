package finalProject.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import finalProject.common.BaseClass;
import finalProject.factory.BrowserName;
import finalProject.factory.WebDriverFactory;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class Hooks {

    public WebDriver driver;
    public Logger logger = BaseClass.getLogger();

    @Before(value="@TestUI")
    public void Setup() {
        System.out.println("Setup");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        //Получаем имя браузера из параметра -Dbrowser командной строки, если не указан то по умолчанию chrome
        String name = Optional.ofNullable(System.getProperty("browser")).orElse("chrome");

        //Получаем имя драйвера из класса Enum
        BrowserName browserName = BrowserName.findByName(name);

        //Если имя браузера не было распознано корректно, то логируем предупреждение
        if (browserName == BrowserName.DEFAULT) {
            logger.warn("WebDriver name from the cmdline is not recognized %" + name
                    + "% use Chrome");
        }

        //Создаём вебдрайвер через статический метод класса WebDriverFactory
        driver = WebDriverFactory.create(browserName);
        //Устанавливаем максимальный размер окна для браузера
        driver.manage().window().maximize();
        logger.info("Start WebDriver " + browserName.getBrowserName());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        BaseClass.setDriver(driver);
    }

    @After(value="@TestUI")
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Shutdown WebDriver");
        } else {
            logger.error("Error WebDriver not found");
        }
    }
}