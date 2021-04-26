package wtf.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для действий с элементами связанных с вводом информации
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Input extends Base {

    /**
     * Метод для ввода текста в элемент обозначенный локатором
     *
     * @param locator    локатор элемента By
     * @param text текст для ввода String
     * @return текущий класс
     */
    public Input locator(By locator, String text) {
        $(locator)
                .waitUntil(exist, wait)
                .sendKeys(text);
        return this;
    }

    /**
     * Метод для ввода текста в элемент обозначенный локатором
     * с последующим нажатием Enter
     *
     * @param locator    локатор элемента By
     * @param text текст для ввода String
     * @return текущий класс
     */
    public Input locatorEnter(By locator, String text) {
        $(locator)
                .waitUntil(exist, wait)
                .sendKeys(text, Keys.ENTER);
        return this;
    }

}
