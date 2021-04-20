package finalProject.stepdefs;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import finalProject.common.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.AfterStep;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import finalProject.common.BaseClass;
import finalProject.factory.BrowserName;
import finalProject.factory.WebDriverFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.AfterTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class Hooks {

    public WebDriver driver;
    public Logger logger = BaseClass.getLogger();

    @Value("${selenide.browserSize}")
    private String browserSize;

    @Value("${selenide.remoteURL}")
    private String remoteURL;


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

    @Before
    public void setUp() {
        setBrowserConfiguration();
    }

    @AfterStep
    private void setDownTest() {
        Selenide.closeWebDriver();
        logger.info("Закрыт браузер");
    }

    @After(value="@Selenide")
    public void closeSelenide() {
        Selenide.clearBrowserLocalStorage();
        logger.info("Чистим браузер");
    }



    public void setBrowserConfiguration() {
        //Настройка ожиданий
        Configuration.timeout = 10000;
        //Настройка размера браузера из параметров maven, по умолчанию из конфиг файла
        Configuration.browserSize =  Optional.ofNullable(System.getProperty("browserSize")).orElse(browserSize);
        //Настройка удалённого запуска из параметров maven, по умолчанию из конфиг файла
        if (remoteURL.equals("")) remoteURL = null;
        Configuration.remote =  Optional.ofNullable(System.getProperty("remoteURL")).orElse(remoteURL);
        //Получаем имя браузера из параметра -Dbrowser командной строки, если не указан то по умолчанию chrome
        String name = Optional.ofNullable(System.getProperty("browser")).orElse(Browsers.CHROME);
        //Получаем имя драйвера из класса Enum
        BrowserName browserName = BrowserName.findByName(name);
        // настройка листенера Allure
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(true));

        // Задаём настройки удаленного запуска для Chrome
        if (browserName.getBrowserName().equals("chrome")) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Collections.singletonList("--ignore-certificate-errors"));
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("89.0");
            // применяем желаемые настройки удаленного исполнения автотестов
            Configuration.browserCapabilities = capabilities;
        }
        //Указываем браузер
        Configuration.browser = browserName.getBrowserName();
    }
}