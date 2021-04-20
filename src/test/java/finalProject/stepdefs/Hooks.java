package finalProject.stepdefs;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import finalProject.common.BaseClass;
import finalProject.factory.BrowserName;
import finalProject.factory.WebDriverFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
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

    @Before(value="@Selenide")
    public void selenide() {
        System.out.println("Setup selenide");

    }

    @After(value="@TestUI")
    public void closeSelenide() {
        Selenide.closeWebDriver();
        logger.info("Закрыт браузер");
    }



    public void setBrowserConfiguration() {
        Configuration.timeout = 10000; // время implisit wait в мс
        //Настройка размера браузера из параметров maven, по умолчанию из конфиг файла
        Configuration.browserSize =  Optional.ofNullable(System.getProperty("browserSize")).orElse(browserSize);
        //Настройка удалённого запуска из параметров maven, по умолчанию из конфиг файла
        Configuration.remote =  Optional.ofNullable(System.getProperty("remoteURL")).orElse(remoteURL);
        //Выбор браузера  из параметров maven для дальнейшей инициализации, по умолчанию Chrome
        String browserName =  Optional.ofNullable(System.getProperty("browser")).orElse(Browsers.CHROME);
        // настройка листенера Allure
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(true));

        if (Configuration.browser.equals("chrome")) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome(); // создаем желаемые настройки удаленного исполнения автотестов
            capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors")); // параметр для игнорирования ошибок сертификатов SSL в браузере
            capabilities.setBrowserName("chrome"); // выбираем желаемый браузер для удаленного выполнения
            capabilities.setVersion("80.0"); // выбираем желаемую версию браузера для удаленного выполнения
            capabilities.setCapability("enableVNC", true); // true - при запуске на Selenoid можно будет наблюдать за прохождением тестов в графическом интерфейсе Selenoid. Повышает нагрузку на кластер примерно на 5-10%
            capabilities.setCapability("enableVideo", false); // true - при запуске на Selenoid будет сохраняться видеозапись их прохождения. Значительно повышает нагрузку на кластер
            Configuration.browserCapabilities = capabilities; // применяем желаемые настройки удаленного исполнения автотестов
            Configuration.browser = browserName;
        } else {
            Configuration.browser = browserName;
        }
    }
}