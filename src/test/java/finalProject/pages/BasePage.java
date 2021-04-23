package finalProject.pages;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import finalProject.common.BaseClass;

/**
 * Класс для описания базовой страницы
 * с использованием POM
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
@Component
public class BasePage {

    public Logger logger = BaseClass.getLogger();

}
