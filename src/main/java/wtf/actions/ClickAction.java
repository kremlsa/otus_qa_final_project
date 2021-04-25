package wtf.actions;

import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import wtf.uniloc.UniLoc;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ClickAction extends BaseAction {
    /**
     * Метод для нажатия элемента по локатору
     *
     * @param locatorBy элемент By
     * @return текущий класс
     */
    @Step("Нажать на элемент с локатором \"{locatorBy}\"")
    public ClickAction loc(By locatorBy) {
        $(locatorBy)
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
    @Given("Нажать на кнопку с частью текста span {string}")
    public ClickAction buttonSpanTextPart(String spanText) {
        $x(UniLoc.xpathString(UniLoc.SPANCONTAINS, spanText))
                .waitUntil(exist, wait)
                .click();
        return this;
    }
}
