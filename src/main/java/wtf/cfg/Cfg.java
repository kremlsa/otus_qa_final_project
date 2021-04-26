package wtf.cfg;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;
import wtf.common.BrowserName;

import java.util.Collections;
import java.util.Optional;

/**
 * Класс для настройки Selenoide
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Cfg {
    private static String browserSize = "1920x1080";
    private static String remoteURL = "";

    /**
     * Метод для настройки Selenoid
     *
     */
    public static void setBrowserConfiguration() {
        //Настройка ожиданий
        Configuration.timeout = 12000;
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
            //capabilities.setCapability("enableVideo", true);
            // применяем желаемые настройки удаленного исполнения автотестов
            Configuration.browserCapabilities = capabilities;
            Configuration.headless = true;
        }
        //Указываем браузер
        Configuration.browser = browserName.getBrowserName();
    }
}
