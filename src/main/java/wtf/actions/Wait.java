package wtf.actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import wtf.uniloc.UniLoc;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

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
