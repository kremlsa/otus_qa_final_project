package wtf.uniloc;

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
    SPANCONTAINS("//span[contains(text(), '%s')]"),
    EVENTCOUNTER("//span[contains(text(), '%s')]/../span[3]"),
    //Tag
    TAG("//div[contains(@class,'evnt-tag')]/label[contains(text(),'%s')]"),
    //Text value
    TEXTSPAN("//span[text()='%s']"),
    //Default
    DEFAULT("Not Found!!!");

    String locator;

    UniLoc(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }

    /**
     * Метод для формирования xpath локаторов
     *
     * @param parameter параметр для подстановки String
     * @param name имя универсального локатора String
     * @return локатор By.xpathLocator
     */
    public static By xpathLocator(UniLoc name, String parameter) {
        for (UniLoc value: values()) {
            if (value.equals(name)) {
                return By.xpath(String.format(value.locator, parameter));
            }
        }
        return By.xpath(DEFAULT.locator);
    }

    /**
     * Метод для формирования xpath локатора в виде строки
     *
     * @param parameter параметр для подстановки String
     * @param name имя универсального локатора String
     * @return локатор в строковом представлении String
     */
    public static String xpathString(UniLoc name, String parameter) {
        for (UniLoc value: values()) {
            if (value.equals(name)) {
                return String.format(value.locator, parameter);
            }
        }
        return DEFAULT.locator;
    }
}