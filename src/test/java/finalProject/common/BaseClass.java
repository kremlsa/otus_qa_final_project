package finalProject.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Базовый класс
 *
 * @author Alexander Kremlev
 * @version 1.0
 */
@Component
public class BaseClass {
    public static final Logger logger = LogManager.getLogger();

    public static Logger getLogger() {
        return logger;
    }

}