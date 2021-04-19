package finalProject.common;

import finalProject.factory.BrowserName;
import org.openqa.selenium.By;

public enum UniLoc {

    //Label
    LABELDATA("//label[@data-value='%s']"),
    //Div
    DIVCLASS("//div[@class='%s']"),
    //Span
    SPAN("//span[text()='%s']"),
    EVENTCOUNTER("//span[contains(text(), '%s')]/../span[3]"),
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
}