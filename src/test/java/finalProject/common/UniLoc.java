package finalProject.common;

import finalProject.factory.BrowserName;
import org.openqa.selenium.By;

/**
 * Enum для универсальных локаторов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public enum UniLoc {

    //Label
    LABELDATA("//label[@data-value='%s']"),
    //Div
    DIVCLASS("//div[@class='%s']"),
    //Span
    SPAN("//span[text()='%s']"),
    EVENTCOUNTER("//span[contains(text(), '%s')]/../span[3]"),
    //Tag
    TAG("//div[contains(@class,'evnt-tag')]/label[contains(text(),'%s')]"),
    //Default
    DEFAULT("Not Found!!!");

    String locator;

    UniLoc(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }

    //Возвращаем локатор XPath
    public static By xpathLocator(UniLoc name, String parameter) {
        for (UniLoc value: values()) {
            if (value.equals(name)) {
                return By.xpath(String.format(value.locator, parameter));
            }
        }
        return By.xpath(DEFAULT.locator);
    }

    //Возвращаем параметризированную строку XPath
    public static String xpathString(UniLoc name, String parameter) {
        for (UniLoc value: values()) {
            if (value.equals(name)) {
                return String.format(value.locator, parameter);
            }
        }
        return DEFAULT.locator;
    }
}