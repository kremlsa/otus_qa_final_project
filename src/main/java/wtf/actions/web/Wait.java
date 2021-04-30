package wtf.actions.web;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

/**
 * Класс для основных действий связанных с ожиданием элементов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class Wait {
    /**
     * Метод ожидания исчезновения элемента с текстом
     *
     * @param locator  By
     * @param text текст элемента String
     * @param timer время ожидания int
     * @return текущий класс
     */
    public Wait disappearText(By locator, String text, int timer) {
        $(locator)
                .waitUntil(Condition.not(Condition.matchesText(text)), timer);
        return this;
    }

    /**
     * Метод для ожидания появления элемента
     *
     * @param locator  By
     * @return текущий класс
     */
    public Wait exist(By locator) {
        $(locator).should(Condition.exist);
        return this;
    }
}
