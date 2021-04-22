package finalProject.stepdefs;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import finalProject.common.BaseClass;
import finalProject.factory.BrowserName;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import java.util.Collections;
import java.util.Optional;



public class Hooks {

    public WebDriver driver;
    public Logger logger = BaseClass.getLogger();

    @Value("${selenide.browserSize}")
    private String browserSize;

    @Value("${selenide.remoteURL}")
    private String remoteURL;

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
            Configuration.headless = true;
        }
        //Указываем браузер
        Configuration.browser = browserName.getBrowserName();
    }
}