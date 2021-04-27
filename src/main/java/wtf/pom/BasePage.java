package wtf.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.actions.*;

/**
 * Супер класс для наследования в POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class BasePage {

    public static final Logger logger = LogManager.getLogger();
    protected Action action = new Action();
    protected Click click = new Click();
    protected Input input = new Input();
    protected Find find = new Find();
    protected Wait wait = new Wait();


}
