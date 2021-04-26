package wtf.pom;

import wtf.actions.*;

/**
 * Супер класс для наследования в POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class BasePage {

    public Action action = new Action();
    public Click click = new Click();
    public Input input = new Input();
    public Find find = new Find();
    public Wait wait = new Wait();

}
