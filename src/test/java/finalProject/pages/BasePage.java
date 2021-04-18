package finalProject.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import finalProject.common.BaseClass;

@Component
public class BasePage {

    //Объявление веб-драйвера
    protected static WebDriver driver;
    public Logger logger = BaseClass.getLogger();

    public void initWebDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    //Переход по адресу
    public void openURL(String url) {
        driver.get(url);
    }

    //Присутствует ли элемент в DOM
    public boolean elementIsPresent(By selector) {
        return !driver.findElements(selector).isEmpty();
    }

    //Виден ли элемент
    public boolean elementIsDisplayed(By selector) {
        return driver.findElement(selector).isDisplayed();
    }

    //Получить title страницы
    public String getTitle() {
        return driver.getTitle();
    }

    //
    public void scrollAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

}
