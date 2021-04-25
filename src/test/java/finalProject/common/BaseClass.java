package finalProject.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Базовый класс
 *
 * @author Alexander Kremlev
 * @version 1.0
 */
public class BaseClass {
    public static final Logger logger = LogManager.getLogger();

    public static Logger getLogger() {
        return logger;
    }

}