package wtf.actions;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static wtf.actions.Log.logInfo;
import static wtf.actions.Log.logWarn;

public class Find extends Base {
    /**
     * Метод возвращающий текстовое значение элемента
     *
     * @param locator локатор элемента By
     * @return текст элемента String
     */
    public String loc(By locator) {
        return $(locator)
                .waitUntil(exist, wait)
                .getText();
    }

    /**
     * Метод проверяющий наличие текста запроса во всех элементах
     *
     * @param locator локатор элемента By
     * @param query текст запроса String
     * @return наличие текста запроса во всех элементах списка
     */
    public boolean isTextInEachElements(By locator, String query) {
        for (SelenideElement element : $$(locator)) {
            if (!element.getText().contains(query)) {
                //Логируем
                logWarn("Элемент - " + element.getText()
                        + " не содержит запрос - " + query);
                return false;
            } else {
                //Логируем
                logInfo("Элемент - " + element.getText()
                        + " содержит запрос - " + query);
            }
        }
        return true;
    }

    /**
     * Метод возвращющий список из значений атрибутов для элемента
     *
     * @param locator локатор элемента By
     * @param atrName атрибут
     * @return список значений атрибутов
     */
    public List<String> attributesList(By locator, String atrName) {

        List<String> attributes = new ArrayList<>();
        List<SelenideElement> elements = $$(locator);
        //Составляем список значений атрибутов
        for (SelenideElement element : elements) {
            attributes.add(element.getAttribute(atrName));
        }
        return attributes;
    }
}


