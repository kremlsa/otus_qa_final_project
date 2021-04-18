package finalProject.common;

import finalProject.factory.BrowserName;
import org.openqa.selenium.By;

public enum UniLoc {

    LABELCONTAINS("//label[contains(text(),'%s')]"),
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