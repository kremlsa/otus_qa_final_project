package wtf.actions;

import io.qameta.allure.Step;
import wtf.uniloc.UniLoc;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для реализации различных методов Click
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Click extends Base {
    /**
     * Метод для нажатия элемента по локатору
     *
     * @param locator элемент By
     * @return текущий класс
     */
    @Step("Нажать на элемент с локатором \"{locator}\"")
    public Click xpathLocator(String locator) {
        $x(locator)
                .waitUntil(exist, wait)
                .hover()
                .click();
        return this;
    }

    /**
     * Метод для нажатия по совпадению span
     *
     * @param spanText текст на кнопке String
     * @return текущий класс
     */
    @Step("Нажать на кнопку с частью текста span \"{spanText}\"")
    public Click buttonSpanTextPart(String spanText) {
        $x(UniLoc.xpathString(UniLoc.SPANCONTAINS, spanText))
                .waitUntil(exist, wait)
                .click();
        return this;
    }
}
