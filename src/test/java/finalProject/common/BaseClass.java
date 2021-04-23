package finalProject.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

/**
 * Базовый класс
 *
 * @author Alexander Kremlev
 * @version 1.0
 */
@Component
public class BaseClass {
    public static WebDriver driver;
    public static final Logger logger = LogManager.getLogger();

    public static void setDriver(WebDriver driver) {
        BaseClass.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Logger getLogger() {
        return logger;
    }

}