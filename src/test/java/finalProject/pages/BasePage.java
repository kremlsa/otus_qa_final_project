package finalProject.pages;

import org.apache.logging.log4j.Logger;
import finalProject.common.BaseClass;
import wtf.actions.Click;
import wtf.actions.Action;

/**
 * Класс для описания базовой страницы
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class BasePage {

    Action action = new Action();
    Click click = new Click();

    public Logger logger = BaseClass.getLogger();

}
