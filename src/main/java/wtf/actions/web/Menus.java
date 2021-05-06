package wtf.actions.web;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import wtf.actions.Base;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Menus extends Base {

    /**
     * Метод для выбора элемента в dropdown menu
     *
     * @param locator локатор меню By
     * @param element локатор элемента меню By
     * @return текущий класс
     */
    public Menus elementInMenu(By locator, By element) {
        //Открываем
        $(locator)
                .waitUntil(exist, WAIT_TIME)
                .click();
        //Выбираем
        $(element)
                .waitUntil(visible, WAIT_TIME)
                .click();
        //Закрываем
        $(locator)
                .waitUntil(visible, WAIT_TIME)
                .click();
        return this;
    }
}
